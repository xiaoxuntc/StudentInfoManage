package com.java1234.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @Description : 日期工具
 * @Creation Date : 2018/12/3
 * @Author : Sean
 */
public class DateUtil {

    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }
}
