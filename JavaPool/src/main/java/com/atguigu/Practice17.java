package com.atguigu;

// 练习yield函数
public class Practice17 implements Runnable{
    Practice17() {
        // 创建并启动线程
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if((i % 5 == 0 )){
                System.out.println(Thread.currentThread() + "yield cpu...");
                // 当前线程让出CPU执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {
        new Practice17();
        new Practice17();
        new Practice17();
    }
}
