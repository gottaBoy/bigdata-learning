package com.my.hbase.service;

import com.my.hbase.entity.HbaseTest;
import org.apache.commons.collections.map.HashedMap;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class BaseService {

    @Autowired
    private Connection hbaseConnection;

    /**
     * 创建表
     * @param resultClass
     * @param <T>
     * @return
     */
    public <T> boolean create(Class<T> resultClass){
        try{
            TableInfo annotation = resultClass.getAnnotation(TableInfo.class);
            Table table = hbaseConnection.getTable(TableName.valueOf(annotation.value()));
            Admin admin = hbaseConnection.getAdmin();
            if(admin.tableExists(table.getName())){
                return false;
            }
            // 创建表描述类
            Set<String> clumnnFamily = new HashSet<>();
            Field[] declaredFields = resultClass.getDeclaredFields();
            for(Field field : declaredFields) {
                ColumnGroup group = field.getAnnotation(ColumnGroup.class);
                clumnnFamily.add(group.value());
            }
            TableName tableName = table.getName(); // 表名称
            HTableDescriptor desc = new HTableDescriptor(tableName);
            if(clumnnFamily!=null&&clumnnFamily.size()>0){
                for (String columnGroup:clumnnFamily) {
                    // 创建列族的描述类
                    HColumnDescriptor family = new HColumnDescriptor(columnGroup); // 列族
                    // 将列族添加到表中
                    desc.addFamily(family);
                }
            }
            // 创建表
            admin.createTable(desc); // 创建表
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public <T> void add(T data){
        try {
            // 新增数据
            Class<?> aClass = data.getClass();
            TableInfo annotation = aClass.getAnnotation(TableInfo.class);
            Table table = hbaseConnection.getTable(TableName.valueOf(annotation.value()));
            Put put = new Put("001".getBytes());
            Field[] declaredFields = aClass.getDeclaredFields();
            String methodName;
            for (Field field: declaredFields) {
                ColumnGroup group = field.getAnnotation(ColumnGroup.class);
                methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method method = data.getClass().getMethod(methodName);
                Object invoke = method.invoke(data);
                put.addColumn(group.value().getBytes(), field.getName().getBytes(), invoke.toString().getBytes());

            }
            table.put(put);
            table.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 整表查询
     * @param resultClass
     */
    public <T> List<T> scan(Class<T> resultClass){
        List<T> list = new ArrayList<>();
        try {
            Annotation[] annotations = resultClass.getAnnotations();
            TableInfo annotation = resultClass.getAnnotation(TableInfo.class);
            Table table = hbaseConnection.getTable(TableName.valueOf(annotation.value()));
            ResultScanner scanner = table.getScanner(new Scan());
            Map<String, T> resultMap = new HashedMap();
            String rowIndex;
            String columnName;
            String methodName;
            for (Result res : scanner) {
                Cell[] cells = res.rawCells();
                for (Cell cell : cells) {
                    rowIndex = Bytes.toString(CellUtil.cloneRow(cell));
                    T hbaseTest;
                    if (resultMap.containsKey(rowIndex)) {
                        hbaseTest = resultMap.get(rowIndex);
                    } else {
                        hbaseTest = resultClass.newInstance();
                        resultMap.put(rowIndex, hbaseTest);
                    }
                    columnName = Bytes.toString(CellUtil.cloneQualifier(cell));
                    methodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method = HbaseTest.class.getMethod(methodName, String.class);
                    method.invoke(hbaseTest, Bytes.toString(CellUtil.cloneValue(cell)));
                    System.out.println(
                            Bytes.toString(CellUtil.cloneRow(cell)) + "---" +
                                    Bytes.toString(CellUtil.cloneFamily(cell)) + "---" +
                                    Bytes.toString(CellUtil.cloneQualifier(cell)) + "---" +
                                    Bytes.toString(CellUtil.cloneValue(cell)) + "---" +
                                    cell.getTimestamp()
                    );
                }
            }
            scanner.close();
            table.close();

            Set<Map.Entry<String, T>> entries = resultMap.entrySet();
            for (Map.Entry<String, T> entry : entries) {
                list.add(entry.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
