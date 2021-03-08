package com.example.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderRequest implements Serializable {

    private String userId;
    private String productCode;
    private String name;
    private Integer count;
    private BigDecimal amount;

}
