package com.winson.jdkapi.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author winson
 * @date 2021/11/4
 **/
public class DateFormatDemoV1 {

    public static void main(String[] args) throws Exception {

        // 1633017600000
        System.out.println(1633017600000L);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf1.format(new Date());
        System.out.println(str);
        Date parseDate = sdf1.parse(str);
        System.out.println(parseDate.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date = calendar.getTime();
        System.out.println(date);

    }

}
