package com.leovegas.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class TransactionPayload {

  private String id;
  private String accountName;
  private LocalDateTime date;
  private TransactionType type;
  private int amount;
}
