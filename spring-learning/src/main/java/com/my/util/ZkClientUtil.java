package com.my.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkClientUtil {
    /**
     * 获取Curator客户端
     */
    public static CuratorFramework getCuratorFramework() {

        //1、配置重试策略 5000：重试间隔 5：重试次数
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(5 * 1000, 5);

        //2、构造Curator客户端
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("这里填写自己zk的ip地址:2181")
                .connectionTimeoutMs(60 * 1000)
                .sessionTimeoutMs(60 * 1000)
                .retryPolicy(policy).build();

        //3、启动客户端
        client.start();

        //4、输出信息
        System.out.println("zookeeper启动成功，获取到客户端链接");
        return client;
    }
}
