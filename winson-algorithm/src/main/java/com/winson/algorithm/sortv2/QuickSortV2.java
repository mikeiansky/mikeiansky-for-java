package com.winson.algorithm.sortv2;

/**
 * @author mike ian
 * @date 2023/8/20
 * @desc 插入排序，原地排序，非稳定排序
 **/
public class QuickSortV2 {

    public static void sort(int data[], int low, int high) {
        int size = high - low + 1;
        if (size <= 1) {
            return;
        }

        int partion = high;

        for (int i = high - 1; i >= low; i--) {
            if (data[i] > data[high]) {
                partion--;
                if (partion != i) {
                    int temp = data[i];
                    data[i] = data[partion];
                    data[partion] = temp;
                }
            }
        }

        // 交换
        int temp = data[high];
        data[high] = data[partion];
        data[partion] = temp;

        sort(data, low, partion - 1);
        sort(data, partion + 1, high);
    }

    public static void main(String[] args) {
//        int[] data = {5, 2, 9, 1, 7, 6};
        int[] data = {1, 3, 6, 8, 9, 2, 5, 7};
        sort(data, 0, data.length - 1);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

}
