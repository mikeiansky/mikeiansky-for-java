package com.winson.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class DateDemoV1 {

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        System.out.println(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd mm:hh:ss");
        String date = sdf.format(new Date());
        System.out.println(date);

    }

}
