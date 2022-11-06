package com.my.hbase.common;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setData(JSONObject.toJSON(object));
        return result;
    }
}
