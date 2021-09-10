package com.qf.jquery.ajax.utils;

/**
 * 这个类是一个工具类,这个类是处理字符串的工具类
 * 这个工具类现在只有一个操作判断传入的代码是否为空
 */
public class StringUtils {
    public static boolean isEmpty(String source){
        return (source == null || "".equals(source));
    }
}
