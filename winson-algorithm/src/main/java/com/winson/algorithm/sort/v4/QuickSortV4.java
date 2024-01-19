package com.winson.algorithm.sort.v4;

/**
 * @author mike ian
 * @date 2024/1/19
 * @desc 快速排序
 **/
public class QuickSortV4 {

    /**
     * 快速排序思路，选择一个数，将小于这个数的分为一个部分，大于这个数的分为一个部分，最后在合并
     *
     * @param data
     */
    public static void sort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    public static void quickSort(int[] data, int start, int end) {
        if (start < end) {
            int split = partition(data, start, end);
            quickSort(data, start, split - 1);
            quickSort(data, split + 1, end);
        }
    }

    public static int partition(int[] data, int left, int right) {
        int pivot = data[right];
        int index = left;
        for (int i = left; i < right; i++) {
            if (data[i] < pivot) {
                swap(data, index, i);
                index++;
            }
        }
        swap(data, index, right);
        return index;
    }

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int data[] = {26, 11, 56, 16, 30, 29, 99, 64};

        System.out.println("排序前");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

        // data
        sort(data);

        System.out.println();
        System.out.println("排序后");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

    }

}
