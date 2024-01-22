package com.winson.algorithm.sort.v4;

/**
 * @author mike ian
 * @date 2024/1/22
 * @desc 快速排序v4版本
 **/
public class MergeSortV4 {


    public static void main(String[] args) {
        int data[] = {26, 11, 56, 16, 30, 29, 99, 64};

        System.out.println("排序前");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

        data = mergeSort(data, 0, data.length - 1);

        System.out.println();
        System.out.println("排序后");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

    }

    private static int[] mergeSort(int[] data, int start, int end) {
        int length = end - start + 1;
        int[] temp = new int[length];
        // 先拆分，按二分进行拆分
        if (length <= 1) {
            temp[0] = data[start];
            return temp;
        }
        if (length == 2) {
            if (data[start] <= data[end]) {
                temp[0] = data[start];
                temp[1] = data[end];
            } else {
                temp[0] = data[end];
                temp[1] = data[start];
            }
            return temp;
        }

        int mid = start + length / 2;

        // 拆分
        int[] head = mergeSort(data, start, mid);
        int[] tail = mergeSort(data, mid + 1, end);

        int headLength = head.length;
        int tailLength = tail.length;

        // 排序
        int headIndex = 0;
        int tailIndex = 0;
        for (int i = 0; i < length; i++) {
            if (headIndex >= headLength) {
                temp[i] = tail[tailIndex];
                tailIndex++;
            } else if (tailIndex >= tailLength) {
                temp[i] = head[headIndex];
                headIndex++;
            } else {
                if (head[headIndex] <= tail[tailIndex]) {
                    temp[i] = head[headIndex];
                    headIndex++;
                } else {
                    temp[i] = tail[tailIndex];
                    tailIndex++;
                }
            }
        }

        return temp;
    }

}
