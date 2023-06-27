package classes.example;

import java.util.Arrays;
import java.util.List;

public class MyStream {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Cathy", "David");
        // 使用Stream过滤以D开头的名字,然后转换成大写
        names.stream()
                .filter(name -> name.startsWith("D"))
                .map(String::toUpperCase)
                .forEach(System.out::println);


    }
}
