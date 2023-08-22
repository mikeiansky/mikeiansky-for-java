package com.winson.algorithm.sort.v3;

/**
 * @author mike ian
 * @date 2023/8/22
 * @desc
 **/
public class QuickSortV3V2 {

    public static void sort(int data[]) {
        quickSort(data, 0, data.length - 1);
    }

    public static void quickSort(int data[], int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(data, low, high);
        quickSort(data, low, pivot - 1);
        quickSort(data, pivot + 1, high);
    }

    public static int partition(int data[], int low, int high) {
        int p = low - 1;
        for (int i = low; i < high; i++) {
            if (data[i] <= data[high]) {
                p++;
                swap(data, p, i);
            }
        }
        swap(data, p + 1, high);
        return p + 1;
    }

    public static void swap(int data[], int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int data[] = {1, 3, 6, 8, 9, 2, 5, 7};
        sort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }

    }

}
