package io.github.mikeiansky.java.base.jdk.arrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author mike ian
 * @date 2024/12/16
 * @desc
 **/
public class ArraysCopyOfDemo {

    public static void main(String[] args) {

        String[] titles = new String[]{
                "tow",
                "one",
                null,
                "three"
        };

        System.out.println(titles.length);
//        Arrays.sort(titles);
        System.out.println(Arrays.stream(titles).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(titles).filter(Objects::nonNull).collect(Collectors.joining(",")));

        String[] nt = Arrays.copyOf(titles, titles.length - 1);
        System.out.println(nt.length);

    }

}
