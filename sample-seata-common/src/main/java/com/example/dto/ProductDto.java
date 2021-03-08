package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private Integer id;
    private String productCode;
    private String name;
    private Integer count;

}
