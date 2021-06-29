package com.winson.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author winson
 * @date 2021/6/29
 **/
public class TimeZoneDemo {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("origin-date:" + date);

        System.out.println("======== cst-format-date ========");
        SimpleDateFormat cstSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cstSdfFormatDateStr = cstSdf.format(date);
        System.out.println("cst-format-date:" + cstSdfFormatDateStr);

        System.out.println("======== gmt-format-date ========");
        SimpleDateFormat gmtSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sssZ");
        String gmtSdfFormatDateStr = gmtSdf.format(date);
        System.out.println("gmt-format-date:" + gmtSdfFormatDateStr);

        System.out.println("======== utc-format-date ========");
        SimpleDateFormat utcSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcSdf.setTimeZone(TimeZone.getTimeZone("America/Denver"));
        String utfSdfFormatDateStr = utcSdf.format(date);
        System.out.println("utc-format-date:" + utfSdfFormatDateStr);

        System.out.println("======== Default TimeZone ========");
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("default time zone : " + timeZone);
        System.out.println("displayName:" + timeZone.getDisplayName());
        System.out.println("ID:" + timeZone.getID());
        System.out.println("RawOffset:" + timeZone.getRawOffset());

        System.out.println("======== America/Denver ========= ");
        TimeZone americaTimeZone = TimeZone.getTimeZone("America/Denver");
        System.out.println("default time zone : " + americaTimeZone);
        System.out.println("displayName:" + americaTimeZone.getDisplayName());
        System.out.println("ID:" + americaTimeZone.getID());
        System.out.println("RawOffset:" + americaTimeZone.getRawOffset());

    }

}
