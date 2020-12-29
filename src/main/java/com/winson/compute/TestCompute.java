package com.winson.compute;

import java.util.Properties;

/**
 * @author winson
 * @date 2020/12/29
 * @desc 计算机相关知识
 **/
public class TestCompute {

    public static void main(String[] args) {
        System.out.println("CPU 个数：" + Runtime.getRuntime().availableProcessors());
        System.out.println("虚拟机内存最大总量：" + Runtime.getRuntime().totalMemory()/(1024f*1024f));
        System.out.println("properties -------> ");
        Properties properties = System.getProperties();
        properties.list(System.out);
    }

}
