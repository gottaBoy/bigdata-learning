package com.my.hbase.controller;

import com.my.hbase.common.Result;
import com.my.hbase.common.ResultUtil;
import com.my.hbase.service.HbaseTestSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hbase")
public class HbaseTestController {
    @Autowired
    HbaseTestSerice hbaseService;
    /**
     * 整表检索
     * @return
     */
    @GetMapping(value = "/query")
    public Result query() {
        return ResultUtil.success(hbaseService.query());
    }
}
