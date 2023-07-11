package com;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

public class demo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String classname = "/Users/liang/Desktop/人生规划/知识营销/ClassesParper/SpringDemo/src/main/java/com/test.java";
//        Class claz = Class.forName(classname);
//        Object obj = claz.newInstance();
//        claz.getName();
        Class claz = String.class;
        // 获取所有公共字段，包括从超类继承的
        Field[] fields = claz.getFields();
        printMembers(fields);

        Method[] methods = claz.getMethods();
        printMembers(methods);

    }

    public static void printMembers(Member[] members){
        Arrays.stream(members).forEach(member -> {
            // 获取并打印成员名称
            System.out.println(member.getName());

            System.out.println(member.getDeclaringClass());
            // 获取并打印成员的修饰符
            System.out.println(member.getModifiers());
        });
    }
}
