package com.atguigu;

import java.util.concurrent.CountDownLatch;

// 演示CountDownLatch
public class Practice12 {
    // 6个同学全部离开后，才可以锁门
    public static void main(String[] args) throws InterruptedException {
        // 创建CountDownLatch对象
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread( () -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
                // 计数 -1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();

        }
        // 等待
        countDownLatch.await();
        System.out.println("锁门");
    }
}
