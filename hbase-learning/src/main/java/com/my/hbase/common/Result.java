package com.my.hbase.common;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;
}
