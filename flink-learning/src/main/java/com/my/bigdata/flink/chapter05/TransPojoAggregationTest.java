package com.my.bigdata.flink.chapter05;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransPojoAggregationTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<Event> stream = env.fromElements(
                new Event("Mary", "./home", 1000L),
                new Event("Bob", "./cart", 2000L),
                new Event("Mary", "./cart", 3000L),
                new Event("Mary", "./fav", 4000L)
        );

        stream.keyBy(e -> e.user)
//                .maxBy("timestamp")
                .max("timestamp")    // 指定字段名称
                .print();

        env.execute();
    }
}

