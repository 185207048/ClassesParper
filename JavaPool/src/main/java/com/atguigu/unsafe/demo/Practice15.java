package com.atguigu.unsafe.demo;

import sun.misc.Unsafe;

public class Practice15 {
    static final Unsafe unsafe = Unsafe.getUnsafe();
    // 记录变量state在类中的偏移量
    static long stateOffset;
    // 变量
    private volatile long state = 0;

    static {
        try{
            // 获取到state变量在类中的偏移量
            stateOffset = unsafe.objectFieldOffset(Practice15.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Practice15 practice15 = new Practice15();
        Boolean success = unsafe.compareAndSwapInt(practice15, stateOffset, 0 , 1);
        System.out.println(success);
    }
}
