package com.example.account.dal.entities;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String userId;
    private Double balance;
}
