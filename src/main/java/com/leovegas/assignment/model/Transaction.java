package com.leovegas.assignment.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private int id;
    private LocalDateTime date;
    private TransactionType type;
    private int amount;
    private boolean successful;
}


