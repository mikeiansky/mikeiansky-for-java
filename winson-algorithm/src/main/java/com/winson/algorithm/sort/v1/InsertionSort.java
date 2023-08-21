package com.winson.algorithm.sort.v1;

/**
 * @author mike ian
 * @date 2023/8/16
 * @desc 插入排序
 **/
public class InsertionSort {

    // 插入排序，a表示数组，n表示数组大小
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j + 1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j + 1] = value; // 插入数据
        }
    }

    public static void main(String[] args) {
        int data[] = {9, 5, 3, 7, 1, 2, 4, 6, 8};
        insertionSort(data, data.length);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }

//        for (int i=1;i>2;i++) {
//            System.out.println(i);
//        }


    }

}
