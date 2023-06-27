package classes.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamUseByContainer {
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


    }
}
