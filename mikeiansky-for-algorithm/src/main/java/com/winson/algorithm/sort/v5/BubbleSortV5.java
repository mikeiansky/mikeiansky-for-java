package com.winson.algorithm.sort.v5;

import java.util.Arrays;

public class BubbleSortV5 {

    public static void main(String[] args) {

        int data[] = {26,11,56,16,30,29,99,64};
        System.out.println("before sort");
        System.out.println(Arrays.toString(data));
        for (int i =0; i< data.length - 1; i++) {
            for (int j=0; j< data.length - i - 1; j++) {
                if (data[j]>data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
        System.out.println("after sort");
        System.out.println(Arrays.toString(data));

    }

}
