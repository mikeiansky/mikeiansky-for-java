package com.winson.algorithm.sort;

/**
 * @author com.winson
 * @date 2020/11/30
 * @desc 快速排序
 **/
public class QuickSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before quick sort");
        SortUtil.printArr(arr);

//        quickSort(arr, 0, arr.length - 1);
        quick_sort(arr, 0, arr.length - 1);
//        quickSort(arr, 0, arr.length - 1, 3);
//        GoodQuickSort.quickSort(arr, arr.length);

        System.out.println("after quick sort");
        SortUtil.printArr(arr);
    }

    public static void quickSort(int[] qa, int p, int r) {
        int length = r - p;
        if (length <= 1) {
            return;
        }
        int split = p;
        int pivot = r;
        int pivotValue = qa[pivot];
        for (int i = p; i <= r; i++) {
            if (qa[i] < pivotValue) {
                if (i != split) {
                    int temp = qa[split];
                    qa[split] = qa[i];
                    qa[i] = temp;
                }
                split++;
            }
        }

        int temp = qa[pivot];
        qa[pivot] = qa[split];
        qa[split] = temp;
        pivot = split;


        quickSort(qa, p, pivot - 1);
        quickSort(qa, pivot + 1, r);
    }


    /*
     * 快速排序
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     l -- 数组的左边界(例如，从起始位置开始排序，则l=0)
     *     r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public static void quick_sort(int a[], int l, int r) {
        if (l < r) {
            int i, j, x;
            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quick_sort(a, l, i - 1); /* 递归调用 */
            quick_sort(a, i + 1, r); /* 递归调用 */
        }
    }

}
