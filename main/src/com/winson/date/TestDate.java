package com.winson.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * @author winson
 * @date 2020/12/16
 **/
public class TestDate {

    public static void main(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-24 09:00:00");
        Instant reqInstant = date.toInstant();
//        System.out.println("now-date:"+new Date());
//        System.out.println("now-date:"+new Date().getTime());
        System.out.println("date:"+new Date());
        System.out.println("systemt-time:"+System.currentTimeMillis());
        System.out.println("instant-time:"+reqInstant.toEpochMilli());
//        System.out.println("instant-now:"+Instant.now());
//        System.out.println("instant-time:"+Instant.now().toEpochMilli());
//        System.out.println("now:"+new Date());
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH,-23);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH)+1;
//        int day = calendar.get(Calendar.DAY_OF_MONTH);

//        System.out.println(year);
//        System.out.println(month);
//        System.out.println(day);




    }

}
