package com.winson.algorithm.sortv2;

/**
 * @author mike ian
 * @date 2023/8/20
 * @desc 插入排序，原地排序，稳定排序
 **/
public class InsertionSortV2 {

    public static void sort(int data[]) {
        for (int i = 1; i < data.length; i++) {
            int value = data[i];
            int index = i;
            for (int j = i; j > 0; j--) {
                if (data[j] > value) {
                    data[j + 1] = data[j];
                    index = j;
                } else {
                    break;
                }
            }
            data[index] = value;
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
