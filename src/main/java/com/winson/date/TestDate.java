package com.winson.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * @author com.winson
 * @date 2020/12/16
 **/
public class TestDate {

    public static void main(String[] args) throws ParseException {
//        String dateStr = "2020-1-29 01:01:01";
//        String dateStr = "2017-9";
//        String dateStr = "0000-00";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        System.out.println("没有格式化之前");
//        System.out.println(sdf.parse(dateStr));
//        System.out.println("格式化之后");
//        System.out.println(sdf.format(sdf.parse(dateStr)));
//        Date date = sdf.parse(dateStr);
//        Calendar c = Calendar.getInstance();
//        System.out.println(c.getTime());
//        c.setTime(date);
//        System.out.println(c.getTime());
//        c.add(Calendar.YEAR,-4);
//        System.out.println(c.getTime());
//        System.out.println(sdf.format(dateStr));
//
//        String one = "2020-12-14 12:12:12";
//        String two = "2020-12-13 12:12:10";
//        System.out.println(one.compareTo(two));

//        String dateOne = "2020-10";
//        SimpleDateFormat sdf = new SimpleDateFormat(dateOne);

//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, -30);

//        System.out.println(calendar.getTime());

        long t = 1623048321000l;
        Date date = new Date(t);
        System.out.println(date.toString());

    }

}
