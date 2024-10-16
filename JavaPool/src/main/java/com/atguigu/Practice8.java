package com.atguigu;

import java.util.concurrent.locks.ReentrantLock;
// 公平锁与非公平锁
public class Practice8 {
    // 票数量
    private int number = 30;
    // 创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);
    // 买票方法
    public void sale() {
        // 上锁
        lock.lock();
        try {
            // 判断是否有票
            if(number > 0 ){
                System.out.println(Thread.currentThread().getName() + "卖出" + (number--) + "剩余:" + number);
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Practice8 practice8 = new Practice8();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                practice8.sale();
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                practice8.sale();
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                practice8.sale();
            }
        },"CC").start();
    }
}
