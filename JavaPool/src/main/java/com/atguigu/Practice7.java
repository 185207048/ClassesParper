package com.atguigu;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*演示List线程不安全*/
public class Practice7 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        /**
         * 证明思路：现在多个线程利用add方法向list中加入数据
         */
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合中添加内容
                set.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合获取内容
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
