package com.example.testdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * @Description: long类型转换成日期
     *
     * @param lo 毫秒数
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String longToDate(long lo){
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
}
