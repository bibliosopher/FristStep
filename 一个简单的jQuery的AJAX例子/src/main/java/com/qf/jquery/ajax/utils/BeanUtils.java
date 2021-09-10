package com.qf.jquery.ajax.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 这个类是一个工具类,具体作用是获取配置文件sql.properties中的sql语句的,
 * 通过getSql这个静态方法可以在配置文件中查询你所需要的sql语句
 */
public class BeanUtils {
    private static Properties properties = new Properties();

    /**
     * 执行一次加载配置文件的静态代码块
     */
    static {
        InputStream inputStream = DBUtils.class.getResourceAsStream("/sql.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取sql语句
     * @param key
     * @return
     */
    public static String getSql(String key){
        return properties.getProperty(key);
    }
}
