package com.qf.jquery.ajax.utils;


import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 这个类是一个工具类,主要作用是获取jdbc连库操作
 * 这个工具类中使用了Alibaba的DruidDataSource
 */
public class DBUtils {
    private static DataSource dataSource;

    static {
        try {
            InputStream inputStream = DBUtils.class.getResourceAsStream("/jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = new DruidDataSource();

            ((DruidDataSource) dataSource).setDriverClassName(properties.getProperty("jdbc.driver"));
            ((DruidDataSource) dataSource).setUsername(properties.getProperty("jdbc.username"));
            ((DruidDataSource) dataSource).setPassword(properties.getProperty("jdbc.password"));
            ((DruidDataSource) dataSource).setUrl(properties.getProperty("jdbc.url"));

            ((DruidDataSource) dataSource).setMaxActive(20);
            ((DruidDataSource) dataSource).setMinIdle(2);
            ((DruidDataSource) dataSource).setInitialSize(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

}
