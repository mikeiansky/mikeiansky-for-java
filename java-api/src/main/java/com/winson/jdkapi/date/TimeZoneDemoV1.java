package com.winson.jdkapi.date;

import java.util.TimeZone;

/**
 * @author winson
 * @date 2021/9/8
 **/
public class TimeZoneDemoV1 {

    public static void main(String[] args) {

        String[] idList = TimeZone.getAvailableIDs();
        for (String id : idList) {
            System.out.println(id);
        }

    }

}
