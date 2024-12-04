package com.winson.utils.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class PrintUtils {

    public static List toList(Object[] arr) {
        if (arr == null) {
            return new ArrayList();
        }
        return Arrays.stream(arr).collect(Collectors.toList());
    }

}
