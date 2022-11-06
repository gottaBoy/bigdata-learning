package com.my.hbase.service;

import com.my.hbase.entity.HbaseTest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HbaseTestSerice extends BaseService {

    /**
     * 全数据查询
     * @return
     */
    public List<HbaseTest> query(){
        return this.scan(HbaseTest.class);
    }
}