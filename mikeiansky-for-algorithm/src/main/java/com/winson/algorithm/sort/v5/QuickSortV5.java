package com.winson.algorithm.sort.v5;

import java.util.Arrays;

public class QuickSortV5 {

    // 快速排序入口方法
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // 递归快速排序方法
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 1. 找到分区点（pivot），将数组分为两部分
            int pivotIndex = partition(arr, low, high);

            // 2. 递归排序左半部分
            quickSort(arr, low, pivotIndex - 1);

            // 3. 递归排序右半部分
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // 分区方法（核心）：选择一个 pivot，将小于 pivot 的放左边，大于的放右边
    private static int partition(int[] arr, int low, int high) {
        // 选择最右边的元素作为 pivot（也可以选第一个、随机等）
        int pivot = arr[high];

        // i 是小于 pivot 的元素的边界索引
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // 交换 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 4. 最后将 pivot 放到正确的位置，即 i + 1
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // 返回 pivot 的最终索引
        return i + 1;
    }

    public static void main(String[] args) {
        int data[] = {26,11,56,16,30,29,99,64};
        System.out.println("before sort");
        System.out.println(Arrays.toString(data));

        quickSort(data);

        System.out.println("after sort");
        System.out.println(Arrays.toString(data));
    }

}
