package com.test.able.util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JVMMemoryMonitorScheduled extends Thread{
    private int type; // 0 全展示 1展示堆内存 2展示非堆内存
    public JVMMemoryMonitorScheduled(int type){
        this.type = type;
    }

    private void getStackMemory(MemoryMXBean memoryMXBean){
        // 获取堆内存使用情况
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        long heapInit = heapMemoryUsage.getInit() / (1024 * 1024); // 堆内存初始大小
        long heapUsed = heapMemoryUsage.getUsed() / (1024 * 1024); // 已使用的堆内存大小
        long heapCommitted = heapMemoryUsage.getCommitted() / (1024 * 1024); // JVM为堆内存保证可用的内存大小
        long heapMax = heapMemoryUsage.getMax() / (1024 * 1024); // 堆内存的最大值
        // 堆内存使用情况
        System.out.println("堆内存使用情况:");
        // JVM启动时，堆内存被分配的初始大小。
        System.out.println("  初始: " + heapInit + " MB");
        // 当前堆内存中已经被对象实例和数组占用的内存大小。
        System.out.println("  已使用: " + heapUsed + " MB");
        // JVM从操作系统那里确保可用于堆内存的内存大小。
        // 已提交的内存大小可能会随着应用程序的运行而增加（如果堆内存需要扩展），但一旦提交，它就不会被释放回操作系统，除非JVM关闭。
        System.out.println("可使用: " + heapCommitted + " MB");
        // 堆内存可以扩展到的最大大小。如果JVM试图分配的内存超过了这个大小，它将抛出OutOfMemoryError。
        System.out.println("  可扩展大小: " + heapMax + " MB");
    }

    private void getNonStackMemory(MemoryMXBean memoryMXBean){
        // 获取非堆内存使用情况
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        long nonHeapInit = nonHeapMemoryUsage.getInit() / (1024 * 1024); // 非堆内存初始大小
        long nonHeapUsed = nonHeapMemoryUsage.getUsed() / (1024 * 1024); // 已使用的非堆内存大小
        long nonHeapCommitted = nonHeapMemoryUsage.getCommitted() / (1024 * 1024); // JVM为非堆内存保证可用的内存大小
        long nonHeapMax = nonHeapMemoryUsage.getMax() / (1024 * 1024); // 非堆内存的最大值（可能是-1，表示没有最大值）
        // 非堆内存使用情况, 非堆内存（Non-Heap Memory）是JVM中除了堆内存之外的其他内存区域的总和。
        System.out.println("\n非堆内存使用情况:");
        System.out.println("  初始: " + nonHeapInit + " MB");
        System.out.println("  已使用: " + nonHeapUsed + " MB");
        System.out.println("可使用: " + nonHeapCommitted + " MB");
        System.out.println("  可扩展大小: " + (nonHeapMax == -1 ? "未设定" : nonHeapMax + " MB"));
    }
    @Override
    public void run() {
        // 用ScheduledExecutorService来定时获取JVM中的内存
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            // 获取当前日期和时间
            LocalDateTime now = LocalDateTime.now();
            // 默认格式化输出
            System.out.println("当前时间: " + now);
            // 获取MemoryMXBean的实例
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            // 打印内存使用情况
            switch (type){
                case 0:
                    this.getStackMemory(memoryMXBean);
                    this.getNonStackMemory(memoryMXBean);
                    break;
                case 1:
                    this.getStackMemory(memoryMXBean);
                    break;
                case 2:
                    this.getNonStackMemory(memoryMXBean);
                    break;
            }
        };
        // 安排任务每5秒执行一次
        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}
