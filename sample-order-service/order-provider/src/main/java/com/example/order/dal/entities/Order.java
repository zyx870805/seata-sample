package com.example.order.dal.entities;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private String orderNo;
    private String userId;
    private String productCode;
    private int count;
    private double Amount;
}
