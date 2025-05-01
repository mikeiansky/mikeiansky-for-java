package io.github.mikeiansky.java.base.jdk.enums;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author mike ian
 * @date 2024/12/30
 * @desc
 **/
public class EnumDemo {

    public enum WeekDay {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    public static void main(String[] args) {
        System.out.println(Arrays.stream(WeekDay.values()).map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println(WeekDay.MON);
        System.out.println(WeekDay.MON.name());
//        System.out.println(WeekDay.valueOf("mon"));
//        System.out.println(WeekDay.valueOf("mOn"));
        System.out.println(WeekDay.valueOf("MON"));
//        ByteBuffer.allocateDirect(1);

    }

}
