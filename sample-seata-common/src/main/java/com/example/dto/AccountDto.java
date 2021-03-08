package com.example.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDto implements Serializable {

    private Integer id;
    private String userId;
    private BigDecimal balance;

}
