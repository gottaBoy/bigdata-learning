package com.my.hbase.config;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HBaseConfig {
    @Value("${hbase.zookeeper.quorum}")
    private String zookeeperQuorum;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String clientPort;

    @Value("${zookeeper.znode.parent}")
    private String znodeParent;

    @Bean
    public Connection hbaseConnection() {
        Connection conn = null;
        try {
            conn = ConnectionFactory.createConnection(configuration());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public org.apache.hadoop.conf.Configuration configuration() {
        org.apache.hadoop.conf.Configuration hbaseConf = new org.apache.hadoop.conf.Configuration();
        hbaseConf.set("hbase.zookeeper.quorum", zookeeperQuorum);
        hbaseConf.set("hbase.zookeeper.property.clientPort", clientPort);
        hbaseConf.set("zookeeper.znode.parent", znodeParent);
        return hbaseConf;
    }
}