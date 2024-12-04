package com.winson.algorithm.sort.v3;

/**
 * @author mike ian
 * @date 2023/8/21
 * @desc 归并排序
 **/
public class MergeSortV3 {

    public static void sort(int data[]) {
        int temp[] = new int[data.length];
        mergeSort(data, temp, 0, data.length - 1);
    }

    public static void mergeSort(int data[], int temp[], int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(data, temp, left, mid);
            mergeSort(data, temp, mid + 1, right);
            merge(data, temp, left, mid, right);
        }
    }

    public static void merge(int data[], int temp[], int left, int mid, int right) {
        // 填充左半部分数据，从小到大排序
        for (int i = left; i <= mid; i++) {
            temp[i] = data[i];
        }

        // 填充右半部分数据，从大到小排序
        for (int j = right; j > mid; j--) {
            temp[mid + 1 + right - j] = data[j];
        }

        int i = left;
        int j = right;

        // 怎么使用它
        for (int k = left; k <= right; k++) {
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
