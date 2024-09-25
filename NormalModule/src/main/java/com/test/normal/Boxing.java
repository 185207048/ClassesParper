package com.test.normal;

public class Boxing {
    // 装箱
    public static void boxing(){
        // 赋值引用变量intvalue
        int intvalue = 11;
        // 手动装箱
        Integer integer = Integer.valueOf(intvalue);
        System.out.println(integer);
    }

    // 拆箱
    public static void unboxing(){
        Integer integer = 11;
        int result = integer;
        System.out.println(result);
    }

    // 自动拆箱
    public static void main(String[] args) {
        boxing();
        unboxing();
    }
}
