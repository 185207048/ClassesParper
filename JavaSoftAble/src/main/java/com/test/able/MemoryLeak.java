package com.test.able;

import com.test.able.util.JVMMemoryMonitorScheduled;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
class A{
    int index;
    B b;
}

class B{
    int index;
    A a;
}

class QueryCache{
    // 静态的HashMap，意味着它在JVM的生命周期内都存在，存放在堆内存（Heap Memory）
    // java -Xms200m -Xmx250m program, JVM堆的初始大小为200MB，最大大小为250MB
    private static final A[] cache = new A[999999];
    // 添加查询结果到缓存
    public static void putResult( int index, Double item) {
        A a = new A();
        B b = new B();
        a.index = index;
        b.index = index;
        a.b = b;
        b.a = a;
        cache[index] = a;
    }
    public static int getCache(int index){
//        int result = 0 ;
//        for (int i = 0; i < index; i++) {
//            result += cache[i].index;
//        }
        return cache[index].index;
    }
}
class DatabaseQuery{
    // 模拟执行查询并缓存结果
    public void executeQueryAndCache(int index, Double result){
        // 将结果添加到缓存中
        QueryCache.putResult(index, result);
    }

    public void getQueryAndCache(int index){
        // 将结果添加到缓存中
        int result = QueryCache.getCache(index);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/liang/Desktop/人生规划/知识营销/ClassesParper/JavaSoftAble/log.txt", true);
            fileOutputStream.write(Integer.toString(result).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
class Loop extends Thread{
    DatabaseQuery query = new DatabaseQuery();
    private int index;
    private int start;
    public Loop(int index, int start){
        this.index = index;
        this.start = start;
    }
    @Override
    public void run() {
        for (int i = start; i < index * 10000; i++) {
            query.executeQueryAndCache( i , (double) i);
            query.getQueryAndCache(i);
        }
    }

}

public class MemoryLeak {
    public static void main(String[] args) {
        JVMMemoryMonitorScheduled jvmMemoryMonitorScheduled = new JVMMemoryMonitorScheduled(1);
        jvmMemoryMonitorScheduled.start();
        // 0 - 10000
        // 10000 - 20000
        // 20000 - 30000
        // 30000 - 40000
        // 40000 - 50000
        // 50000 - 60000
        // 70000 - 80000
        // 80000 - 90000
        // 90000 - 100000

        Loop loop1 = new Loop(0, 1);
        loop1.start();
        Loop loop2 = new Loop(10000, 2);
        loop2.start();
        Loop loop3 = new Loop(20000, 3);
        loop3.start();
        Loop loop4 = new Loop(30000, 4);
        loop4.start();
        Loop loop5 = new Loop(40000, 5);
        loop5.start();
        Loop loop6 = new Loop(50000, 6);
        loop6.start();
        Loop loop7 = new Loop(60000, 7);
        loop7.start();
    }
}
