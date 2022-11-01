package com.leovegas.assignment.model;

import lombok.Data;

import java.util.List;

@Data
public class account {

    private String name;
    private int balance;
    private List<Transaction> transactions;

}
