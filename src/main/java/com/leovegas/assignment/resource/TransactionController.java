package com.leovegas.assignment.resource;

import com.leovegas.assignment.model.Account;
import com.leovegas.assignment.model.AccountList;
import com.leovegas.assignment.model.TransactionPayload;
import com.leovegas.assignment.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

  private final TransactionService service;

  public TransactionController(TransactionService service) {
    this.service = service;
  }

  @PostMapping(path = "api/accounts/debit")
  public ResponseEntity<String> debitAccount(@RequestBody TransactionPayload payload)
      throws Exception {
    return service.debitAccount(payload);
  }

  @PostMapping(path = "api/accounts/credit")
  public ResponseEntity<String> creditAccount(@RequestBody TransactionPayload payload)
      throws Exception {
    return service.creditAccount(payload);
  }

  @GetMapping(path = "api/accounts/get")
  public ResponseEntity<Account> getAccount(
      @RequestParam(name = "accountName") String accountName) {
    return service.getAccount(accountName);
  }

  @GetMapping(path = "api/accounts/getAll")
  public ResponseEntity<AccountList> getAllAccounts() {
    return service.getAllAccounts();
  }
}
