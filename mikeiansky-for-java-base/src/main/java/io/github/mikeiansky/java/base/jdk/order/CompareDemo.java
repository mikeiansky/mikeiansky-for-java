package io.github.mikeiansky.java.base.jdk.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author mike ian
 * @date 2024/12/18
 * @desc
 **/
public class CompareDemo {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class One {
        private int order;
        private String tag;
    }

    public static class SimpleOrder implements Comparator<One> {

        @Override
        public int compare(One o1, One o2) {
            return o1.order - o2.order;
        }

    }

    public static void main(String[] args) {
        List<One> oneList = List.of(
                new One(3, "3"),
                new One(4, "4"),
                new One(7, "7"),
                new One(2, "2-1"),
                new One(2, "2-0"),
                new One(1, "1"),
                new One(5, "5")
        );
        oneList = new ArrayList<>(oneList);

        System.out.println("before sort");
        System.out.println(oneList);

        System.out.println("after sort");
        oneList.sort(new SimpleOrder());
        System.out.println(oneList);


    }

}
