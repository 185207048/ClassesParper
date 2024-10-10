package com.atguigu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share{
    private int shareNumber = 0;
    private final Lock lockshare = new ReentrantLock();
    private final Condition condition = lockshare.newCondition();
    // +1
    public void incr(String threadName){
        for (int i = 0 ; i < 10 ; i ++){
            lockshare.lock();
            try {
                while(shareNumber != 0){
                    condition.await();
                }

                shareNumber++;
                System.out.println(threadName + "::" + shareNumber);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockshare.unlock();
            }
        }
    }
    // -1
    public void decr(String threadName){
       for (int i = 0 ; i < 10 ; i ++){
            lockshare.lock();
            try {
                while(shareNumber != 1){
                    condition.await();
                }
                shareNumber--;
                System.out.println(threadName + "::" + shareNumber);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockshare.unlock();
            }
        }
    }
}

public class Practice2 {
    public static void main(String[] args) {
        Share share = new Share();
        Thread threadA = new Thread(() -> {
            share.incr("AA");
        },"AA");
        Thread threadB = new Thread(() -> {
            share.decr("BB");
        },"BB");
        Thread threadC = new Thread(() -> {
            share.incr("CC");
        },"CC");
        Thread threadD = new Thread(() -> {
            share.decr("DD");
        },"DD");
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

    }
}
