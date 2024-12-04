package com.winson.algorithm.sort.v4;

/**
 * @author mike ian
 * @date 2024/1/19
 * @desc 插入排序
 **/
public class InsertSortV4 {

    /**
     * 插入排序思路，以从小到大排序为例
     * <p>
     * 从前到后遍历数据，索引位置，插入到小于第一数的前面
     *
     * 是原地排序吗：是
     * 是稳定排序吗：是
     *
     * @param data
     */
    public static void sort(int[] data) {

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    break;
                }
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
