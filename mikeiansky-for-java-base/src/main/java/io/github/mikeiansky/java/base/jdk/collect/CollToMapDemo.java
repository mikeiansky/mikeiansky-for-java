package io.github.mikeiansky.java.base.jdk.collect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mike ian
 * @date 2024/12/19
 * @desc
 **/
public class CollToMapDemo {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class One {
        private int id;
        private String name;
    }

    public static void main(String[] args) {

        List<One> oneList = List.of(
                new One(1, "a"),
                new One(2, null),
                new One(3, "a")
        );

        Map<Integer,String> avatarMap = oneList.stream().collect(
                Collectors.toMap(One::getId, One::getName, (key1,key2)-> key2)
        );
        System.out.println(avatarMap);

    }

}
