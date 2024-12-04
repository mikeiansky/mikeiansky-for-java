package com.winson.algorithm.sort.v3;

/**
 * @author mike ian
 * @date 2023/8/21
 * @desc
 **/
public class QuickSortV3 {

    public static void sort(int data[]) {
        subsort(data, 0, data.length - 1);
    }

    public static void subsort(int data[], int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(data, low, high);
        subsort(data, low, pivot - 1);
        subsort(data, pivot + 1, high);
    }

    public static int partition(int data[], int low, int high) {
        int pivot = high;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (data[j] <= data[pivot]) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, pivot);
        return i + 1;
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
