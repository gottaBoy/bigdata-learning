package com.my.bigdata.flink.chapter07;

import com.my.bigdata.flink.chapter05.ClickSource;
import com.my.bigdata.flink.chapter05.Event;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

public class ProcessFunctionTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        env
                .addSource(new ClickSource())
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Event>forMonotonousTimestamps()
                                .withTimestampAssigner(new SerializableTimestampAssigner<Event>() {
                                    @Override
                                    public long extractTimestamp(Event event, long l) {
                                        return event.timestamp;
                                    }
                                })
                )
                .process(new ProcessFunction<Event, String>() {
                    @Override
                    public void processElement(Event value, Context ctx, Collector<String> out) throws Exception {
                        if (value.user.equals("Mary")) {
                            out.collect(value.user);
                        } else if (value.user.equals("Bob")) {
                            out.collect(value.user);
                            out.collect(value.user);
                        }
                        System.out.println(ctx.timerService().currentWatermark());
                        System.out.println(ctx.timestamp());
                    }
                })
                .print();

        env.execute();
    }
}

