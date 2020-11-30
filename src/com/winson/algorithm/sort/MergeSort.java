package com.winson.algorithm.sort;

/**
 * @author winson
 * @date 2020/11/30
 * @desc 归并排序
 **/
public class MergeSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before merge sort");
        SortUtil.printArr(arr);
        arr = mergeSort(arr, 0, arr.length - 1);
        System.out.println("after merge sort");
        SortUtil.printArr(arr);
    }

    public static int[] mergeSort(int[] ma, int start, int end) {
        int length = end - start + 1;
        if (length == 2) {
            if (ma[start] > ma[end]) {
                return new int[]{ma[end], ma[start]};
            } else {
                return new int[]{ma[start], ma[end]};
            }
        }
        if (length <= 1) {
            return new int[]{ma[start]};
        }
        int split = length / 2;
        int[] prefixArr = mergeSort(ma, start, start + split - 1);
        int[] suffixArr = mergeSort(ma, start + split, end);
        int[] result = new int[length];
        int prefixIndex = 0;
        int suffixIndex = 0;
        for (int i = 0; i < length; i++) {
            if (prefixIndex >= prefixArr.length) {
                result[i] = suffixArr[suffixIndex];
                suffixIndex++;
                continue;
            }
            if (suffixIndex >= suffixArr.length) {
                result[i] = prefixArr[prefixIndex];
                prefixIndex++;
                continue;
            }
            if (prefixArr[prefixIndex] > suffixArr[suffixIndex]) {
                result[i] = suffixArr[suffixIndex];
                suffixIndex++;
            } else {
                result[i] = prefixArr[prefixIndex];
                prefixIndex++;
            }
        }
        return result;
    }

}
