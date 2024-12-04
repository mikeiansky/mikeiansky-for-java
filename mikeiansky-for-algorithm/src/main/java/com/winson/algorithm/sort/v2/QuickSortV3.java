package com.winson.algorithm.sort.v2;

/**
 * @author mike ian
 * @date 2023/8/20
 * @desc 快速排序，原地排序，不稳定排序
 **/

public class QuickSortV3 {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 数组为空或只有一个元素时，无需排序
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right); // 获取基准元素的索引
            quickSort(arr, left, pivotIndex - 1); // 对基准元素左边的子数组进行快速排序
            quickSort(arr, pivotIndex + 1, right); // 对基准元素右边的子数组进行快速排序
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 选择最右边的元素作为基准元素
        int i = left - 1; // i 表示小于基准元素的区域的右边界

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j); // 将小于等于基准元素的元素交换到左边区域
            }
        }

        swap(arr, i + 1, right); // 将基准元素放到正确的位置上
        return i + 1; // 返回基准元素的索引
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 6};
        quickSort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
