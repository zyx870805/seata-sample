package com.example.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDto implements Serializable {

    private String orderNo;
    private String userId;
    private String productCode;
    private Integer orderCount;
    private BigDecimal orderAmount;

}
