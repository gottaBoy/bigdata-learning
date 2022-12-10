package com.my.bigdata.mapreduce.calculate.min;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MinTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int minValue = Integer.MAX_VALUE;
        for (IntWritable value : values) {
            // 最小值
            minValue = Math.min(minValue, value.get());
        }
        context.write(key, new IntWritable(minValue));

    }
}
