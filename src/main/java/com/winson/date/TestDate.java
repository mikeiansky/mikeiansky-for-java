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
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-24 09:00:00");
//        Instant reqInstant = date.toInstant();
//        System.out.println("now-date:"+new Date());
//        System.out.println("now-date:"+new Date().getTime());
//        System.out.println("date:"+new Date());
//        System.out.println("systemt-time:"+System.currentTimeMillis());
//        System.out.println("instant-time:"+reqInstant.toEpochMilli());
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

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = sdf.parse("2020-12-01 00:00:00");
//        Date date2 = sdf.parse("2020-12-03 00:00:00");
//        System.out.println(date2.after(date1));
//        System.out.println(sdf.format(date2));

//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.HOUR_OF_DAY, -8);
//        System.out.println(calendar.getTime());

        String dateStr = "2020-01-29 01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(sdf.parse(dateStr)));

    }

}
