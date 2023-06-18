package com.my.bigdata;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

public class FlinkSQLCDC {

    public static void main(String[] args) throws Exception {

        //1.获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        //2.使用FLINKSQL DDL模式构建CDC 表
        tableEnv.executeSql("CREATE TABLE test ( " +
                " user_id INTEGER primary key, " +
                " url STRING, " +
                " event_time TIMESTAMP " +
                ") WITH ( " +
                " 'connector' = 'mysql-cdc', " +
//                " 'scan.startup.mode' = 'latest-offset', " +
                " 'hostname' = 'localhost', " +
                " 'port' = '3306', " +
                " 'username' = 'root', " +
                " 'password' = 'My@123456', " +
                " 'database-name' = 'test', " +
                " 'table-name' = 'test' " +
                ")");

        //3.查询数据并转换为流输出
        Table table = tableEnv.sqlQuery("select * from test");
        DataStream<Tuple2<Boolean, Row>> retractStream = tableEnv.toRetractStream(table, Row.class);
        // (true,+I[1, /test, 2022-12-23T16:56:53])
        retractStream.print();

        //4.启动
        env.execute("FlinkSQLCDC");

    }

}
