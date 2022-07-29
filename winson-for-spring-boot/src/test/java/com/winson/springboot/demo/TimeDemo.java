package com.winson.springboot.demo;

import java.util.Date;

/**
 * @author winson
 * @date 2022/7/29
 **/
public class TimeDemo {

    public static void main(String[] args) {

        long time = 1659088495451l;
        Date dt = new Date(time);
        System.out.println(dt);

    }

}
