package com.my.bigdata.flink.wordcount;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.MemorySize;
import org.apache.flink.configuration.TaskManagerOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class Stream {
    public static void main(String[] args) throws Exception {
        // 1. 创建流式执行环境
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Configuration configuration = new Configuration();
        // 网络缓存占flink总内存的百分比
        configuration.setFloat(TaskManagerOptions.NETWORK_MEMORY_FRACTION, 0.1f);
        // 网络缓存的最小内存占用
        configuration.set(TaskManagerOptions.NETWORK_MEMORY_MIN, MemorySize.parse("1g"));
        // 网络缓存的最大内存占用
        configuration.set(TaskManagerOptions.NETWORK_MEMORY_MAX, MemorySize.parse("1g"));
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);

        // 2. 读取文本流
        DataStreamSource<String> lineDSS = env.socketTextStream("hdp101", 7777);
        // 3. 转换数据格式
        SingleOutputStreamOperator<Tuple2<String, Long>> wordAndOne = lineDSS
                .flatMap((String line, Collector<String> words) -> {
                    Arrays.stream(line.split(" ")).forEach(words::collect);
                })
                .returns(Types.STRING)
                .map(word -> Tuple2.of(word, 1L))
                .returns(Types.TUPLE(Types.STRING, Types.LONG));
        // 4. 分组
        KeyedStream<Tuple2<String, Long>, String> wordAndOneKS = wordAndOne
                .keyBy(t -> t.f0);
        // 5. 求和
        SingleOutputStreamOperator<Tuple2<String, Long>> result = wordAndOneKS
                .sum(1);
        // 6. 打印
        result.print();
        // 7. 执行
        env.execute();
    }
}
