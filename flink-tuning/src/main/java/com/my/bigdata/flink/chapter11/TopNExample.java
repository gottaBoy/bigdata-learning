package com.my.bigdata.flink.chapter11;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;


public class TopNExample {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        // 1. 在创建表的DDL中直接定义时间属性
        String createDDL = "CREATE TABLE clickTable (" +
                " `user` STRING, " +
                " url STRING, " +
                " ts BIGINT, " +
                " et AS TO_TIMESTAMP( FROM_UNIXTIME(ts / 1000) ), " +
                " WATERMARK FOR et AS et - INTERVAL '1' SECOND " +
                ") WITH (" +
                " 'connector' = 'filesystem', " +
                " 'path' = 'input/clicks.csv', " +
                " 'format' =  'csv' " +
                ")";

        tableEnv.executeSql(createDDL);

        // 普通Top N，选取当前所有用户中浏览量最大的2个

        Table topNResultTable = tableEnv.sqlQuery("SELECT user, cnt, row_num " +
                "FROM (" +
                "   SELECT *, ROW_NUMBER() OVER (" +
                "      ORDER BY cnt DESC" +
                "   ) AS row_num " +
                "   FROM (SELECT user, COUNT(url) AS cnt FROM clickTable GROUP BY user)" +
                ") WHERE row_num <= 2");

        tableEnv.toChangelogStream(topNResultTable).print("top 2: ");

        env.execute();
    }
}
