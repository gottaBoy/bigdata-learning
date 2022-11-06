package com.my.hbase.common;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
}
