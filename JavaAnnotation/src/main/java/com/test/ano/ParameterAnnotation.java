package com.test.ano;

import java.lang.reflect.Method;

class TestAnnotation{
    public void test(@Range Integer order){
        checkRange(order);
        System.out.println(order);
    }

    private void checkRange(Integer order){
        try{
            Method method = this.getClass().getMethod("test", Integer.class);
            Range range = method.getParameters()[0].getAnnotation(Range.class);
            if(range != null){
                if(order < range.getMin() || order > range.getMax()){
                    throw new IllegalArgumentException("Order must be between 1 and 100");
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
public class ParameterAnnotation {
    public static void main(String[] args) {
        TestAnnotation testAnnotation = new TestAnnotation();
        testAnnotation.test(200);
    }

}
