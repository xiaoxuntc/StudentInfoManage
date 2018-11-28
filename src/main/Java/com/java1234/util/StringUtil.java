package com.java1234.util;

/***
* @Description : 字符串工具类
* @Creation Date : 2018/11/17
* @Author : Sean
*/
public class StringUtil {

    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        if (!"".equals(str) && str != null) {
            return true;
        } else {
            return false;
        }
    }
}
