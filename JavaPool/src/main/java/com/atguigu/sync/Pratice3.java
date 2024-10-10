package com.atguigu.sync;

/**
 * 虚假唤醒的例子
 */
class Share{

    private int num = 0 ;

    public synchronized void incr() throws InterruptedException {
        if(num != 0 ){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "::" + num);
        this.notifyAll();
    }

    public synchronized void desc() throws InterruptedException {
        if(num != 1 ){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "::" + num);
        this.notifyAll();
    }

}
public class Pratice3 {
    public static void main(String[] args) {
        Share share = new Share();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC");
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
