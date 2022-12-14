package com.leovegas.assignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leovegas.assignment.model.Account;
import com.leovegas.assignment.model.AccountList;
import com.leovegas.assignment.model.Transaction;
import com.leovegas.assignment.model.TransactionPayload;
import com.leovegas.assignment.util.Utility;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

  private static AccountList accountList;

  public ResponseEntity<String> debitAccount(TransactionPayload payload) throws Exception {
    Account account = readAccountFromFile(payload.getAccountName());
    checkTransactionId(account, payload);

    if (account.getBalance() >= payload.getAmount()) {
      account.setBalance(account.getBalance() - payload.getAmount());
      addTransactionToAccount(account, payload, true);
      return ResponseEntity.ok("Account [" + payload.getAccountName() + "] successfully debited");
    }

    addTransactionToAccount(account, payload, false);
    return ResponseEntity.ok("Unable to debit account, insufficient funds!");
  }

  public ResponseEntity<String> creditAccount(TransactionPayload payload) throws Exception {
    Account account = readAccountFromFile(payload.getAccountName());
    checkTransactionId(account, payload);

    account.setBalance(account.getBalance() + payload.getAmount());
    addTransactionToAccount(account, payload, true);

    return ResponseEntity.ok("Account [" + account.getName() + "] successfully credited");
  }

  public ResponseEntity<Account> getAccount(String accountName) {
    return ResponseEntity.ok(readAccountFromFile(accountName));
  }

  public ResponseEntity<AccountList> getAllAccounts() {
    return ResponseEntity.ok(accountList);
  }

  public ResponseEntity<String> createAccount(String accountName) throws Exception {
    Account account =
        Account.builder().name(accountName).transactions(new ArrayList<>()).balance(0).build();

    accountList.getAccounts().add(account);
    saveUpdatedAccounts();

    return ResponseEntity.ok("Account created for " + accountName);
  }

  private void addTransactionToAccount(
      Account account, TransactionPayload payload, boolean successful) throws Exception {
    List<Transaction> transactions = account.getTransactions();
    transactions.add(
        Transaction.builder()
            .id(payload.getId())
            .date(payload.getDate())
            .type(payload.getType())
            .amount(payload.getAmount())
            .successful(successful)
            .build());

    for (Account acc : accountList.getAccounts()) {
      if (acc.getName().equals(account.getName())) {
        acc.setTransactions(transactions);
      }
    }
    saveUpdatedAccounts();
  }

  private void saveUpdatedAccounts() throws Exception {
    ObjectMapper om = Utility.objectMapper();
    om.writeValue(new File("src/main/resources/accounts.json"), accountList);
  }

  private void checkTransactionId(Account account, TransactionPayload payload) {
    for (Transaction transaction : account.getTransactions()) {
      if (transaction.getId().equals(payload.getId())) {
        throw new RuntimeException("Duplicate transaction ID in payload");
      }
    }
  }

  private Account readAccountFromFile(String accountName) {
    for (Account account : accountList.getAccounts()) {
      if (account.getName().equals(accountName)) {
        return account;
      }
    }

    throw new RuntimeException("Could not find account in DB: " + accountName);
  }

  @PostConstruct
  void loadAccounts() throws Exception {
    Utility.init();
    accountList =
        Utility.objectMapper()
            .readValue(new File("src/main/resources/accounts.json"), AccountList.class);
  }
}
