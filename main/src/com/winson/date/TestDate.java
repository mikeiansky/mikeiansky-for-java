package com.winson.date;

import java.util.Calendar;

/**
 * @author winson
 * @date 2020/12/16
 **/
public class TestDate {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println(year);
        System.out.println(month);
        System.out.println(day);


    }

}
