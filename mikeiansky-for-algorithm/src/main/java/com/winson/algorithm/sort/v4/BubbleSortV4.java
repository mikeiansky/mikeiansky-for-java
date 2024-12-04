package com.winson.algorithm.sort.v4;

/**
 * @author mike ian
 * @date 2024/1/19
 * @desc 冒泡排序V4版本
 * 冒泡排序思路，以从前往后排为例子，先排好后面的数
 * 从前到后，相邻两个数进行排序，保证小在前，大在后
 * 是原地排序吗，是
 * 是稳定排序吗，是
 **/
public class BubbleSortV4 {


    public static void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int data[] = {26,11,56,16,30,29,99,64};

        System.out.println("排序前");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }
        System.out.println();
        sort(data);
        System.out.println("排序后");
        for (int datum : data) {
            System.out.print(datum);
            System.out.print(',');
        }

    }

}
