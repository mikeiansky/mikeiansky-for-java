package com.winson.algorithm.sort;

/**
 * @author mike ian
 * @date 2023/8/10
 * @desc 冒泡排序
 **/
public class BubbleSort {

    public static void bubbleSort(int data[]) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int data[] = {9, 5, 3, 7, 1, 2, 4, 6, 8};
        bubbleSort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

}
