package com.winson.algorithm.sort;

/**
 * @author mike ian
 * @date 2023/8/16
 * @desc
 **/
public class SelectionSort {

    public static void selectionSort(int data[]) {
        for (int i = 1; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[minIndex] > data[j]) {
                    minIndex = j;
                }
            }
//            if (data[i - 1] > data[minIndex]) {
                int temp = data[i - 1];
                data[i - 1] = data[minIndex];
                data[minIndex] = temp;
//            }
        }
    }

    public static void main(String[] args) {
        int data[] = {3, 1, 6, 8, 9, 2, 5, 7};
        selectionSort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

}
