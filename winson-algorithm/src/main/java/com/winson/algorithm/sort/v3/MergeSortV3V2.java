package com.winson.algorithm.sort.v3;

/**
 * @author mike ian
 * @date 2023/8/22
 * @desc 归并排序
 **/
public class MergeSortV3V2 {

    public static void sort(int data[]) {
        int start = 0;
        int end = data.length - 1;
        int temp[] = new int[data.length];
        mergeSort(data, temp, start, end);
    }

    public static void mergeSort(int data[], int temp[], int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(data, temp, start, mid);
        mergeSort(data, temp, mid + 1, end);
        merge(data, temp, start, mid, end);
    }

    public static void merge(int data[], int temp[], int start, int mid, int end) {
        // 左半部分，正序处理
        for (int i = start; i <= mid; i++) {
            temp[i] = data[i];
        }

        // 右半部分，逆序处理
        for (int j = end; j > mid; j--) {
            temp[j] = data[mid + end - j + 1];
        }

        int i = start;
        int j = end;
        for (int k = start; k <= end; k++) {
            if (temp[i] <= temp[j]) {
                data[k] = temp[i];
                i++;
            } else {
                data[k] = temp[j];
                j--;
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
