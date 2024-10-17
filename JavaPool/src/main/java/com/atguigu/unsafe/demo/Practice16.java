package com.atguigu.unsafe.demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Practice16 {
    static Unsafe unsafe = null;
    static long stateOffset ;
    static int[] stateOffsetArrary = new int[]{1,2,3,4};
    private volatile long state = 0;
    static {
        try{
            // 使用反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置可存取
            field.setAccessible(true);
            // 获取该变量的值
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(Practice16.class.getDeclaredField("state"));
            System.out.println("objectFieldOffset 指定变量偏移量:" + stateOffset);
            System.out.println("arrayBaseOffset 获取数组中第一个元素地址:" + unsafe.arrayBaseOffset(stateOffsetArrary.getClass()));
            System.out.println("arrayIndexScale 获取数组中第一个元素占用的字节:" + unsafe.arrayIndexScale(stateOffsetArrary.getClass()));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Practice16 practice16 = new Practice16();
        Boolean success = unsafe.compareAndSwapInt(practice16, stateOffset, 0 ,1);

        System.out.println("compareAndSwapInt 比较对象obj中偏移量为offset的变量的值是否与expect相等，如果相等用update的值进行更新:"
                + success);

        System.out.println("getLongVolatile 获取对象obj中偏移量为offset的变量对应volatile语义的值 state: "
                + unsafe.getLongVolatile(practice16, 16));

        unsafe.putLongVolatile(practice16, stateOffset, 2);
        System.out.println("修改后的state值: " + unsafe.getLongVolatile(practice16, 16));

        unsafe.putOrderedObject(practice16, stateOffset, 3);
        System.out.println("putOrderedLong 延时型putLongVolatile，修改后不对其他线程立刻见效，只有在变量使用volatile修饰并且遇见会被以外修改时才使用改方法: " + unsafe.getLongVolatile(practice16, 16));

        // CAS原子操作更新值
        long l;
        long update = 10;
        do{
            l = unsafe.getLongVolatile(practice16, stateOffset);
        }while (!unsafe.compareAndSwapLong(practice16, stateOffset, l, update));
        System.out.println("修改后的state值: " + unsafe.getLongVolatile(practice16, 16));

        // 获取偏移量同时设置变量为 x + addValue
        long l2;
        long update2 = 100;
        do{
            l2 = unsafe.getLongVolatile(practice16, stateOffset);
        }while (!unsafe.compareAndSwapLong(practice16, stateOffset, l2, l2 + update2));
        System.out.println("修改后的state值: " + unsafe.getLongVolatile(practice16, 16));


    }
}
