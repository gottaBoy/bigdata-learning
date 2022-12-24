package com.my.bigdata.ct.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 分析数据Mapper
 */
public class AnalysisTextMapper extends TableMapper<Text, Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        String rowkey = Bytes.toString(key.get());
        // 5_19154926260_20180802160747_16574556259_0054_1

        String[] values = rowkey.split("_");

        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];

        String year = calltime.substring(0, 4);
        String month = calltime.substring(0, 6);
        String date = calltime.substring(0, 8);

        // 主叫用户 - 年
        context.write(new Text(call1 + "_" + year), new Text(duration));
        // 主叫用户 - 月
        context.write(new Text(call1 + "_" + month), new Text(duration));
        // 主叫用户 - 日
        context.write(new Text(call1 + "_" + date), new Text(duration));

        // 被叫用户 - 年
        context.write(new Text(call2 + "_" + year), new Text(duration));
        // 被叫用户 - 月
        context.write(new Text(call2 + "_" + month), new Text(duration));
        // 被叫用户 - 日
        context.write(new Text(call2 + "_" + date), new Text(duration));
    }
}
