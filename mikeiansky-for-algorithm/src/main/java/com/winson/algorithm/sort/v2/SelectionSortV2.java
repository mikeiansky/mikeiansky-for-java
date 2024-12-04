package com.winson.algorithm.sort.v2;

/**
 * @author mike ian
 * @date 2023/8/20
 * @desc 选择排序，原地排序，不稳定排序
 **/
public class SelectionSortV2 {

    public static void sort(int data[]) {

        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[minIndex] > data[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int data[] = {1, 3, 6, 8, 9, 2, 5, 7};
        sort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

}
