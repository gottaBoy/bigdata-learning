package com.my.hbase.entity;

import com.my.hbase.service.ColumnGroup;
import com.my.hbase.service.TableInfo;
import lombok.Data;

@Data
@TableInfo(value = "hbase_test")
public class HbaseTest {
    @ColumnGroup(value="cf1")
    private String gender;
    @ColumnGroup(value="cf1")
    private String name;
    @ColumnGroup(value="cf2")
    private String chinese;
    @ColumnGroup(value="cf2")
    private String math;
}
