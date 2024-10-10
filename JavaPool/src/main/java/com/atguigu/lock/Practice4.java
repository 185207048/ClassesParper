package com.atguigu.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 指定唤醒的例子
 */
class Share4{
    private int shareNumber = 0;
    // +1
    public void incr(Lock lockshare, Condition condition, Condition nextCondition){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            for (int i = 0 ; i < 10 ; i ++){
                lockshare.lock();
                while(shareNumber != 0){
                    condition.await();
                }
                shareNumber++;
                System.out.println(Thread.currentThread().getName() + "::" + shareNumber);
                nextCondition.signal();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockshare.unlock();
        }
    }
    // -1
    public void decr(Lock lockshare, Condition condition, Condition nextCondition){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            for (int i = 0 ; i < 10 ; i ++){
                lockshare.lock();
                    while(shareNumber != 1){
                        condition.await();
                    }
                    shareNumber--;
                    System.out.println(Thread.currentThread().getName() + "::" + shareNumber);
                    nextCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockshare.unlock();
        }
    }
}

public class Practice4 {
    private final static Lock lockshare = new ReentrantLock();
    private final Condition condition1 = lockshare.newCondition();
    private final Condition condition2 = lockshare.newCondition();
    private final Condition condition3 = lockshare.newCondition();
    private final Condition condition4 = lockshare.newCondition();

    public static void main(String[] args) {
        Share4 share = new Share4();
        Practice4 practice3 = new Practice4();
        Thread threadA = new Thread(() -> {
            share.incr(lockshare, practice3.condition1, practice3.condition2);
        },"AA");
        Thread threadB = new Thread(() -> {
            share.decr(lockshare, practice3.condition2, practice3.condition3);
        },"BB");
        Thread threadC = new Thread(() -> {
            share.incr(lockshare, practice3.condition3, practice3.condition4);
        },"CC");
        Thread threadD = new Thread(() -> {
            share.decr(lockshare, practice3.condition4, practice3.condition1);
        },"DD");
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

    }
}
