package com.atguigu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于演示定制化通信
 */
// 第一步创建资源类
class Share6{
    // 定义标志位
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    // 创建三个Condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印5次，参数第几轮
    public void print5(int loop){
        // 上锁
        lock.lock();
        try{
            // 判断
            while(flag != 1){
                // 等待
                c1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮盘" + loop);
            }
            // 通知
            flag = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 打印10次，参数第几轮
    public void print10(int loop){
        // 上锁
        lock.lock();
        try{
            // 判断
            while(flag != 2){
                // 等待
                c2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮盘" + loop);
            }
            // 通知
            flag = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 打印10次，参数第几轮
    public void print15(int loop){
        // 上锁
        lock.lock();
        try{
            // 判断
            while(flag != 3){
                // 等待
                c3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮盘" + loop);
            }
            // 通知
            flag = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
public class Practice6 {
    public static void main(String[] args) {
        Share6 share = new Share6();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                share.print5(i);
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                share.print10(i);
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                share.print15(i);
            }
        },"CC").start();
    }
}
