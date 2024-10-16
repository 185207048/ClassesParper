package com.atguigu;

import sun.misc.Unsafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 读写锁例子
public class Practice10 {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private int sharedResource = 0 ;

    public void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " read " + sharedResource);
        }finally {
            readLock.unlock();
        }
    }

    public void write(int value){
        writeLock.lock();
        try{
            sharedResource = value;
            System.out.println(Thread.currentThread().getName() + " write " + sharedResource);
        }finally {
            writeLock.unlock();
        }
    }
    public static void main(String[] args) {
        Practice10 practice10 = new Practice10();
        Runnable readTask = () -> {
            for (int i = 0; i < 10; i++) {
                practice10.read();
                try{
                    // 假装做一些处理
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 5; i++) {
                practice10.write(i);
                try {
                    Thread.sleep(150);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(readTask, "Reader1");
        Thread thread2 = new Thread(readTask, "Reader2");
        Thread thread3 = new Thread(writeTask, "Write1");
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
