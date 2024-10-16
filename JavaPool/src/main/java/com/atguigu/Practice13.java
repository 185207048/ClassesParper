package com.atguigu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Practice13 {
    // 创建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        // 创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER , () ->{
            System.out.println("集齐7颗召唤神龙");
        });
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < NUMBER; i++) {
                new Thread( () -> {
                    System.out.println(Thread.currentThread().getName() + "星龙珠");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }, String.valueOf(i)).start();
            }
        }

    }
}
