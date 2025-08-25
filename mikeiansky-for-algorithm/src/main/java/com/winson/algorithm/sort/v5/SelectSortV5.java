package com.winson.algorithm.sort.v5;

import java.util.Arrays;

public class SelectSortV5 {

    public static void main(String[] args) {

        int data[] = {26,11,56,16,30,29,99,64};
        System.out.println("before sort");
        System.out.println(Arrays.toString(data));

        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if  (minIndex != i) {
                int temp = data[minIndex];
                data[minIndex] = data[i];
                data[i] = temp;
            }
        }

        System.out.println("after sort");
        System.out.println(Arrays.toString(data));

    }

}
