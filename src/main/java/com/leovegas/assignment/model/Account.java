package com.leovegas.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  @JsonProperty("account-name")
  private String name;

  @JsonProperty("account-balance")
  private int balance;

  @JsonProperty("account-transactions")
  private List<Transaction> transactions;
}
