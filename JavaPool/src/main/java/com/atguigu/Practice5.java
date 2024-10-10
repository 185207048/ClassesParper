package com.atguigu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于演示多线程编写的步骤
 * 用于演示定制化通信：A打印5次通知B，B打印10次通知C，C打印15次通知A
 */
class Share{
    private int number = 0;
    public void output(int loopnum, Lock lockshare, Condition condition, Condition nextCondition){
        try {
            lockshare.lock();
            // 判断
            while(number != 0){
                condition.await();
            }
            for (int i = 0; i < loopnum; i++) {
                number++;
                // 干活
                System.out.println(Thread.currentThread().getName()  + ":: output" + " " + number);
            }
            // 通知
            nextCondition.signal();
            number = 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lockshare.unlock();
        }
    }
}
public class Practice5 {
    // 1、申请调用资源
    private final static Lock shareLock = new ReentrantLock();
    private final Condition condition1 = shareLock.newCondition();
    private final Condition condition2 = shareLock.newCondition();
    private final Condition condition3 = shareLock.newCondition();

    public static void main(String[] args) {
        // 2、编写资源类方法Share
        Share share = new Share();
        Practice5 practice5 = new Practice5();
        // 3、调用资源
        Thread thread1 = new Thread(() -> share.output(5, shareLock, practice5.condition1, practice5.condition2), "A");
        Thread thread2 = new Thread(() -> share.output(10, shareLock, practice5.condition2, practice5.condition3), "B");
        Thread thread3 = new Thread(() -> share.output(15, shareLock, practice5.condition3, practice5.condition1), "C");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
