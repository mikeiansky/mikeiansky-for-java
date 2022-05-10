package com.winson.manage;

import java.lang.management.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2022/5/10
 **/
public class JvmManageDemoV1 {

    public static void main(String[] args) {

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println(memoryMXBean.getHeapMemoryUsage().getMax()/(1024*1024));
        System.out.println(memoryMXBean.getNonHeapMemoryUsage());

        List<GarbageCollectorMXBean> gcBeanList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeanList) {
            System.out.println(gcBean.getName());
            System.out.println(gcBean.getCollectionCount());
            System.out.println(Arrays.stream(gcBean.getMemoryPoolNames()).collect(Collectors.toList()));
            System.out.println(gcBean.getCollectionTime());
        }

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(threadMXBean.getThreadCount());
        System.out.println(threadMXBean.getThreadInfo(2).getBlockedCount());
        System.out.println(threadMXBean.getTotalStartedThreadCount());

        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getName());
        System.out.println(operatingSystemMXBean.getAvailableProcessors());
        System.out.println(operatingSystemMXBean.getArch());
//        System.out.println(operatingSystemMXBean.getSystemLoadAverage());

        System.out.println(ManagementFactory.getRuntimeMXBean().getClassPath());
        System.out.println(ManagementFactory.getRuntimeMXBean().getBootClassPath());

    }

}
