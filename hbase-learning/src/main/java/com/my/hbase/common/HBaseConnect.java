package com.my.hbase.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.AsyncConnection;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class HBaseConnect {

    // 设置静态属性 hbase 连接
    public static Connection connection = null;

    static {
        // 创建 hbase 的连接
        try {
            connection = ConnectionFactory.createConnection();
        } catch (IOException e) {
            System.out.println("连接获取失败");
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭方法,用于进程关闭时调用
     * @throws IOException
     */
    public static void closeConnection() throws IOException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(HBaseConnect.connection);
    }

    private static void hbaseConnect() throws IOException {
        // 1. 创建配置对象
        Configuration conf = new Configuration();
        // 2. 添加配置参数
        conf.set("hbase.zookeeper.quorum","hdp101,hdp102,hdp103");
        // 3. 创建 hbase 的连接
        // 默认使用同步连接
        Connection connection =
                ConnectionFactory.createConnection(conf);
        // 可以使用异步连接
        // 主要影响后续的 DML 操作
        CompletableFuture<AsyncConnection> asyncConnection =
                ConnectionFactory.createAsyncConnection(conf);
        // 4. 使用连接
        System.out.println(connection);
        // 5. 关闭连接
        connection.close();
    }
}
