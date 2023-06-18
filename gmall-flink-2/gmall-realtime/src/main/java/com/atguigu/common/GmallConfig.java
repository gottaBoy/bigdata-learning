package com.atguigu.common;

public class GmallConfig {
    //Phoenix库名
    public static final String HBASE_SCHEMA = "GMALL_REALTIME";

    //Phoenix驱动
    public static final String PHOENIX_DRIVER = "org.apache.phoenix.jdbc.PhoenixDriver";

    //Phoenix连接参数
    public static final String PHOENIX_SERVER = "jdbc:phoenix:hdp101,hdp102,hdp103:2181";

    //ClickHouse_Url
    public static final String CLICKHOUSE_URL = "jdbc:clickhouse://hdp101:8123/default";

    //ClickHouse_Driver
    public static final String CLICKHOUSE_DRIVER = "ru.yandex.clickhouse.ClickHouseDriver";

}
