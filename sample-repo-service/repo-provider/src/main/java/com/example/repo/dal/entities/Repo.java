package com.example.repo.dal.entities;

import lombok.Data;

@Data
public class Repo {
    private Integer id;
    private String productCode;
    private String name;
    private Integer count;
}
