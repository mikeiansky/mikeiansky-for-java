package com.winson.algorithm.sort.v1;

/**
 * @author mike ian
 * @date 2023/8/18
 * @desc
 **/
public class QuickSort {

    public static void quickSort(int data[], int start, int end) {
        int size = end - start + 1;
        if (size <= 1) {
            return;
        }
        if (size == 2) {
            if (data[start] > data[end]) {
                int temp = data[start];
                data[start] = data[end];
                data[end] = temp;
            }
            return;
        }

        int headIndex = start;
        int tailIndex = end;
        int pivot = start + size / 2;
        int flag = data[pivot];
        int nowPivot = pivot;
        for (int i = start; i < end; i++) {
            if (headIndex >= end) {
                break;
            }
            if (tailIndex <= start) {
                break;
            }
            if (data[headIndex] > flag) {
                // 交换到最后
                int temp = data[headIndex];
                data[headIndex] = data[tailIndex];
                data[tailIndex] = temp;
                nowPivot = tailIndex;
                tailIndex--;
            } else {
                nowPivot = headIndex;
                headIndex++;
            }
        }

        if (pivot < nowPivot) {
            if (data[pivot] >= data[nowPivot]) {
                int temp = data[nowPivot];
                data[nowPivot] = data[pivot];
                data[pivot] = temp;
            }
        } else if (pivot > nowPivot) {
            if (data[pivot] <= data[nowPivot]) {
                int temp = data[nowPivot];
                data[nowPivot] = data[pivot];
                data[pivot] = temp;
            }
        }


        quickSort(data, start, nowPivot - 1);
        quickSort(data, nowPivot + 1, end);
    }

    public static void main(String[] args) {
        int data[] = {1, 4, 5, 7, 3, 9, 2, 6, 8, 10};
        //
        quickSort(data, 0, data.length - 1);
        for (int i : data) {
            System.out.println(i);
        }
    }

}
