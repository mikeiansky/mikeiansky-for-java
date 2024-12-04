package com.winson.algorithm.sort.v4;

/**
 * @author mike ian
 * @date 2024/1/19
 * @desc 归并排序，这是个有问题的版本
 **/
public class MergeSortV4Error {

    /**
     * 归并排序思想，递归方式，将数据进行拆分，排序，然后在合并，有些问题，后面再看
     *
     * @param data
     */
    public static void sort(int[] data, int start, int end) {
        int length = end - start + 1;
        int[] temp = new int[length];
        mergerSort(data, temp, start, end);
    }

    public static void mergerSort(int[] data, int[] temp, int start, int end) {
        int length = end - start + 1;
        if (length == 0) {
            return;
        }
        if (length == 1) {
            temp[start] = data[start];
            return;
        }
        if (length == 2) {
            if (data[start] > data[end]) {
                temp[start] = data[end];
                temp[end] = data[start];
            } else {
                temp[start] = data[start];
                temp[end] = data[end];
            }
            return;
        }

        int mid = start + length / 2;
        mergerSort(data, temp, start, mid);
        mergerSort(data, temp, mid + 1, end);

        // 合并
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                data[k] = temp[j];
                j++;
            } else if (j > end) {
                data[k] = temp[i];
                i++;
            } else {
                if (temp[i] < temp[j]) {
                    data[k] = temp[i];
                    i++;
                } else {
                    data[k] = temp[j];
                    j++;
                }
            }
        }

    }

    public static void main(String[] args) {

        int data[] = {26, 11, 56, 16, 30, 29, 99, 64};

        System.out.println("排序前");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

        sort(data, 0, data.length - 1);

        System.out.println("排序后");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

    }

}
