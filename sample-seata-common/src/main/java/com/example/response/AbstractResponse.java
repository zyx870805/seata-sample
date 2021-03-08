package com.example.response;

import lombok.Data;

@Data
public class AbstractResponse {
    private int code;
    private String msg;
}
