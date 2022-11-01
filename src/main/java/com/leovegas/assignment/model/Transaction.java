package com.leovegas.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private String id;
    private LocalDateTime date;
    private TransactionType type;
    private int amount;
    private boolean successful;
}


