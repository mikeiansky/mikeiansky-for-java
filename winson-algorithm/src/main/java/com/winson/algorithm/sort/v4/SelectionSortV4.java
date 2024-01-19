package com.winson.algorithm.sort.v4;

/**
 * @author mike ian
 * @date 2024/1/19
 * @desc 选择排序
 **/
public class SelectionSortV4 {

    /**
     * 选择排序思路，以从小到大排序为例
     * 从未排序的数据中选取最小的数据，放到排序部分的最末端
     * <p>
     * 是原地排序吗：是
     * 是稳定排序吗：不是
     *
     * @param data
     */
    public static void sort(int[] data) {

        for (int i = 0; i < data.length - 1; i++) {
            int min = data[i];
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < min) {
                    min = minIndex;
                    minIndex = j;
                }
            }
            // 交换
            if (minIndex != i) {
                int temp = data[minIndex];
                data[minIndex] = data[i];
                data[i] = temp;
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

        sort(data);

        System.out.println("排序后");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }
    }

}
