package classes.example;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUseByContainer{
    public static void main(String[] args) {
        // Stream ->
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Cathy", "David");
        // 使用Stream过滤以D开头的名字,然后转换成大写
        names.stream()
                .filter(name -> name.startsWith("D"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // 基本类型
        int a = 1;
        char b = 2;
        boolean c = true;
        short d = 1;
        byte e = 0;
        long f = 2;
        float g = 3;
        double h = 4;
        // 引用类型
        Object object = new Object();
        int[] ins = new int[]{1, 2, 3, 4, 5};

        // 引用管道类型


    }
}

// 先定义一个Pipeline接口
interface Pipeline<T,R>{
    R process(T input);
    default <V> Pipeline<T, V> andThen(Pipeline<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.process(process(t));
    }
}

