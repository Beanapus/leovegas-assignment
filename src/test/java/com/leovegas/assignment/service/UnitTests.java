package com.leovegas.assignment.service;

import com.leovegas.assignment.model.TransactionPayload;
import com.leovegas.assignment.model.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTests {

  private TransactionService service;

  @BeforeEach
  public void setup() throws Exception {
    service = new TransactionService();
    service.loadAccounts();
  }

  @Test
  public void debitAccountTest() throws Exception {
    TransactionPayload payload =
        createPayload(TransactionType.CREDIT, UUID.randomUUID().toString(), 10, "testAccount");
    ResponseEntity<String> response = service.debitAccount(payload);

    assertEquals(ResponseEntity.ok("Account [testAccount] successfully debited"), response);
  }

  @Test
  public void creditAccountTest() throws Exception {
    TransactionPayload payload =
        createPayload(TransactionType.CREDIT, UUID.randomUUID().toString(), 10, "testAccount");
    ResponseEntity<String> response = service.creditAccount(payload);

    assertEquals(ResponseEntity.ok("Account [testAccount] successfully credited"), response);
  }

  @Test
  public void debitAccountFail_insufficientFunds() throws Exception {
    TransactionPayload payload =
        createPayload(TransactionType.DEBIT, UUID.randomUUID().toString(), 100000, "testAccount");
    ResponseEntity<String> response = service.debitAccount(payload);

    assertEquals(ResponseEntity.ok("Unable to debit account, insufficient funds!"), response);
  }

  @Test
  public void debitAccountFail_duplicateId() {
    TransactionPayload payload = createPayload(TransactionType.DEBIT, "1234", 10, "testAccount");

    RuntimeException ex = assertThrows(RuntimeException.class, () -> service.debitAccount(payload));

    assertEquals("Duplicate transaction ID in payload", ex.getMessage());
  }

  @Test
  public void debitAccountFail_accountDoesNotExist() {
    TransactionPayload payload =
        createPayload(TransactionType.DEBIT, UUID.randomUUID().toString(), 10, "unknownAccount");
    RuntimeException ex = assertThrows(RuntimeException.class, () -> service.debitAccount(payload));

    assertEquals("Could not find account in DB: unknownAccount", ex.getMessage());
  }

  private TransactionPayload createPayload(
      TransactionType type, String id, int amount, String accountName) {
    return TransactionPayload.builder()
        .id(id)
        .accountName(accountName)
        .amount(amount)
        .date(LocalDateTime.now())
        .type(type)
        .build();
  }
}
