package com.winson.algorithm.sort;

/**
 * @author com.winson
 * @date 2020/11/30
 * @desc 归并排序
 **/
public class MergeSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before merge sort");
        SortUtil.printArr(arr);

//        arr = mergeSort(arr, 0, arr.length - 1);
//        GoodMergeSort.mergeSort(arr, arr.length);

        mergeSortInternally(arr, 0, arr.length - 1);

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


    public static void mergeSortInternally(int[] ma, int s, int e) {
//        System.out.println("s : " + s + " , e : " + e);
        if (s >= e) {
            return;
        }
        int p = s + (e - s) / 2;
        mergeSortInternally(ma, s, p);
        mergeSortInternally(ma, p + 1, e);

//        mergeNormal(ma, s, p, e);
        mergeSentry(ma, s, p, e);
    }

    public static void mergeNormal(int[] ma, int s, int p, int e) {
        int[] tmp = new int[e - s + 1];

        int i = s;
        int j = p + 1;
        int k = 0;
        while (i <= p && j <= e) {
            if (ma[i] < ma[j]) {
                tmp[k++] = ma[i++];
            } else {
                tmp[k++] = ma[j++];
            }
        }

        int start = i;
        int end = p;
        if (j <= e) {
            start = j;
            end = e;
        }

        while (start <= end) {
            tmp[k++] = ma[start++];
        }

        int a = s;
        int b = e;
        int t = 0;
        while (a <= b) {
            ma[a++] = tmp[t++];
        }

    }

    public static void mergeSentry(int[] ma, int s, int p, int e) {
        int[] headArr = new int[p - s + 2];
        int[] tailArr = new int[e - p + 1];
        int headLength = p - s + 1;
        for (int i = 0; i < headLength; i++) {
            headArr[i] = ma[s + i];
        }
        headArr[headArr.length - 1] = Integer.MAX_VALUE;

        int tailLength = e - p;
        for (int i = 0; i < tailLength; i++) {
            tailArr[i] = ma[p + i + 1];
        }
        tailArr[tailArr.length - 1] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int k = s;
        while (k <= e) {
            if (headArr[i] <= tailArr[j]) {
                ma[k++] = headArr[i++];
            } else {
                ma[k++] = tailArr[j++];
            }
        }

    }

}
