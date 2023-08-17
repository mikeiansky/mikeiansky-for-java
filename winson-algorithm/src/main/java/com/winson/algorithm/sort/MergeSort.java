package com.winson.algorithm.sort;

/**
 * @author mike ian
 * @date 2023/8/17
 * @desc 归并排序
 **/
public class MergeSort {

    public static int[] mergeData(int[] head, int[] tail) {
        int headSize = head.length;
        int tailSize = tail.length;
        int[] result = new int[headSize + tailSize];

        int count = headSize + tailSize;
        int headIndex = 0;
        int tailIndex = 0;

        for (int i = 0; i < count; i++) {
            if (headIndex >= headSize) {
                result[i] = tail[tailIndex];
                tailIndex++;

            } else if (tailIndex >= tailSize) {
                result[i] = head[headIndex];
                headIndex++;
            } else if (head[headIndex] < tail[tailIndex]) {
                result[i] = head[headIndex];
                headIndex++;
            } else {
                result[i] = tail[tailIndex];
                tailIndex++;
            }
        }

        return result;
    }

    public static int[] childMergeSort(int data[], int start, int end) {
        int size = end - start + 1;
//        if (start >= 5) {
//            System.out.println("start : " + start + " , end : " + end + " , size : " + size);
//        }
        int[] result = new int[size];
        if (size == 1) {
            result[0] = data[start];
            return result;
        }
        if (size == 2) {
            if (data[start] > data[end]) {
                result[0] = data[end];
                result[1] = data[start];
            } else {
                result[0] = data[start];
                result[1] = data[end];
            }
            return result;
        }
        int mid = size / 2;
        int[] head = childMergeSort(data, start, start + mid - 1);
        int[] tail = childMergeSort(data, start + mid, end);

        result = mergeData(head, tail);

        return result;
    }

    public static int[] mergeSort(int data[]) {
        // 先分片，再排序，再合并
        int length = data.length;
        int mid = length / 2;

        // 这里有两部分
        int[] head = childMergeSort(data, 0, mid - 1);
        int[] tail = childMergeSort(data, mid, length - 1);

        int[] result = mergeData(head, tail);

        return result;
    }

    public static void main(String[] args) {
        int data[] = {9, 5, 3, 7, 1, 2, 4, 6, 8};
        int[] result = mergeSort(data);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

}
