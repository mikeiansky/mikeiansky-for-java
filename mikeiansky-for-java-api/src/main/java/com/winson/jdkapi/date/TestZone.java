package com.winson.jdkapi.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 测试时区设置的效果
 *
 * @author mike ian
 * @date 2024/6/4
 * @desc
 **/
public class TestZone {

    public static void main(String[] args) throws ParseException {
        String stringDate = "2020-01-02 22:00:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//默认时区解析时间表示
        inputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date1 = inputFormat.parse(stringDate);
        System.out.println(date1 + ":" + date1.getTime());
//纽约时区解析时间表示
        inputFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date2 = inputFormat.parse(stringDate);
        System.out.println(date2 + ":" + date2.getTime());

//        System.out.println(TimeZone.getAvailableIDs());
//        for (String availableID : TimeZone.getAvailableIDs()) {
//            System.out.println(availableID);
//        }

    }

}
