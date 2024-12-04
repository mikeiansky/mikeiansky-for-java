package com.winson.jdkapi.generic.v3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mike ian
 * @date 2023/6/28
 * @desc
 **/
public class GenericConvertBugDemoV1 {

    public static <T, R> void convert(T raw, Set<R> set) {
        set.add((R) raw);
    }

    /**
     * 这是实际开发过程中遇到的问题
     *
     * @param args
     */
    public static void main(String[] args) {

        Set<Integer> temp = new HashSet<>();

        Long one = 1L;
        Long two = 2L;
        Long three = 3L;
        convert(one, temp);
        convert(two, temp);
        // 这里不会报错，因为最终在虚拟机里面这些数据都是Object类型的，所以可以转换
        convert("ccccc", temp);

        // 这也可以。。。。
        List<Integer> result = new ArrayList<>(temp);
        System.out.println(result);

//        for (Object item : result) {
//            System.out.println(item);
//        }

    }

}
