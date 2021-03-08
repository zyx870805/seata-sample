package com.example.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObjectResponse<T> extends AbstractResponse implements Serializable {

    private T data;
}
