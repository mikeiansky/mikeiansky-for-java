package com.winson.algorithm.sort.v5;

import java.util.Arrays;

public class InsertSortV5 {

    public static void main(String[] args) {

        int data[] = {26,11,56,16,30,29,99,64};
        System.out.println("before sort");
        System.out.println(Arrays.toString(data));

        for (int i =1; i< data.length; i++) {
            int key = data[i];
            int j = i-1;
            while (j >=0 && data[j] > key) {
                data[j+1] = data[j];
                j--;
            }
            data[j+1] = key;
        }

        System.out.println("after sort");
        System.out.println(Arrays.toString(data));
    }

}
