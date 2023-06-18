package com.my.bigdata;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

//https://new-developer.aliyun.com/article/1167820

public class FlinkCDCWithSQL {

    public static void main(String[] args) throws Exception {

        //1.获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        String sourceDDL = "CREATE TABLE user_info_source ( " +
                "  `id` bigint,\n" +
                "  `login_name` string,\n" +
                "  `nick_name` string,\n" +
                "  `passwd` string,\n" +
                "  `name` string,\n" +
                "  `phone_num` string,\n" +
                "  `email` string,\n" +
                "  `head_img` string,\n" +
                "  `user_level` string,\n" +
                "  `birthday` string,\n" +
                "  `gender` string,\n" +
                "  `create_time`timestamp,\n" +
                "  `operate_time` timestamp,\n" +
                "  `status` string,\n" +
                "  PRIMARY KEY (`id`) NOT ENFORCED " +
                ") WITH ( " +
                " 'connector' = 'mysql-cdc', " +
                " 'hostname' = 'localhost', " +
                " 'port' = '3306', " +
                " 'username' = 'root', " +
                " 'password' = 'My@123456', " +
                " 'database-name' = 'gmall', " +
                " 'table-name' = 'user_info' " +
                ")";
        String sinkDDL = "CREATE TABLE user_info_kafka_sink ( " +
                "  `id` bigint,\n" +
                "  `login_name` string,\n" +
                "  `nick_name` string,\n" +
                "  `passwd` string,\n" +
                "  `name` string,\n" +
                "  `phone_num` string,\n" +
                "  `email` string,\n" +
                "  `head_img` string,\n" +
                "  `user_level` string,\n" +
                "  `birthday` string,\n" +
                "  `gender` string,\n" +
                "  `create_time`timestamp,\n" +
                "  `operate_time` timestamp,\n" +
                "  `status` string,\n" +
                "  PRIMARY KEY (`id`) NOT ENFORCED " +
                ") WITH ( " +
                " 'connector' = 'upsert-kafka',\n" +
                " 'topic' = 'topic_db',\n" +
                " 'properties.bootstrap.servers' = 'hdp101:9092',\n" +
                " 'properties.group.id' = 'flink-cdc-kafka-group',\n" +
                " 'value.json.timestamp-format.standard' = 'ISO-8601',\n" +
                " 'key.format' = 'json',\n" +
                " 'value.format' = 'json' " +
                ")";
        //2.DDL方式建表
        tableEnv.executeSql(sourceDDL);
//        //3.查询数据
//        Table table = tableEnv.sqlQuery("select * from user_info_source");
//        //4.将动态表转换为流
//        DataStream<Tuple2<Boolean, Row>> retractStream = tableEnv.toRetractStream(table, Row.class);
//        retractStream.print();

        tableEnv.executeSql(sinkDDL);

        String query = "insert into user_info_kafka_sink select * from user_info_source";
        TableResult tr = tableEnv.executeSql(query);
        tr.print();
        //5.启动任务
        env.execute("FlinkCDCWithSQL");

    }

}
