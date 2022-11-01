package com.leovegas.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class TransactionPayload {

    private int id;
    private String accountName;
    private LocalDateTime date;
    private TransactionType type;
    private int amount;

}
