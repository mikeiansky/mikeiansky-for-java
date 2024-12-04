package com.winson.algorithm.sort.v2;

/**
 * @author mike ian
 * @data 2023/08/20
 * @desc 冒泡排序，原地排序，稳定排序
 */
public class BubbleSortV2 {

    public static void sort(int data[]) {
        for (int i = 0; i < data.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    flag = true;
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int data[] = {9, 5, 3, 7, 1, 2, 4, 6, 8};
        sort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }

    }

}
