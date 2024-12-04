package com.winson.algorithm.search.v1;

/**
 * @author mike ian
 * @date 2023/8/22
 * @desc 对已经排序的唯一数组进行二分查找
 **/
public class BinarySearchUniqueV1 {

    public static int binarySearchV2(int data[], int target) {
        int start = 0;
        int end = data.length - 1;
        int mid = start + (end - start) / 2;
        int index = -1;
        do {
            if (data[mid] == target) {
                index = mid;
                break;
            }
            if (data[mid] > target) {
                // 查找前面的
                start = start;
                end = mid - 1;
                mid = start + (end - start) / 2;
            } else {
                // 查找后面的
                start = mid + 1;
                end = end;
                mid = start + (end - start) / 2;
            }

        } while (index >= 0 || mid >= 0);
        return index;
    }

    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                return mid; // 目标值找到，返回索引
            } else if (array[mid] < target) {
                low = mid + 1; // 目标值在右侧，更新下界
            } else {
                high = mid - 1; // 目标值在左侧，更新上界
            }
        }

        return -1; // 目标值未找到
    }

    public static void main(String[] args) {
        int data[] = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 11;
        int index = binarySearch(data, target);
        System.out.println("index : " + index + " , value is : " + data[index]);
    }

}
