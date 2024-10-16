package com.atguigu;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Practice9 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            int sum = 0 ;
            for (int i = 1; i < 100; i++) {
                sum += i;
            }
            return sum;
        };
        // 使用FutureTask包装Callable类
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask, "AA").start();
        System.out.println(futureTask.get());

    }
}
