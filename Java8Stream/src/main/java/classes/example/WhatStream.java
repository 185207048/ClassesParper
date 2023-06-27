package classes.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WhatStream {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Cathy", "David");
        List<String> result = new ArrayList<>();
        // filter(Predicate<T> predicate)
        System.out.println("----------filter(Predicate<T> predicate)----------");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(result::add);
        result.forEach(System.out::println);

        // flatMap(Function<T, Stream<R>> mapper)
        // 使用 flatMap 将每个单词的字符拆分并合并成一个 Stream
        // 移出Stream中的重复元素
        // 将 Stream 中的元素收集到一个集合中
        System.out.println("----------flatMap(Function<T, Stream<R>> mapper)----------");
        result = names.stream()
                .flatMap(name -> Stream.of(name.split("")))
                .distinct()
                .collect(Collectors.toList());
        result.forEach(System.out::println);

        // sorted()
        // 对Stream中的成分进行排序
        System.out.println("----------sorted()----------");
        result = names.stream().sorted().collect(Collectors.toList());
        result.forEach(System.out::println);

        // sorted(Comparator<T> comparator)：根据提供的比较器对 Stream 中的元素进行排序。
        // 根据比较器对成分进行排序
        System.out.println("----------sorted(Comparator<T> comparator)----------");
        // 创建匿名内部类比较器
        Comparator<String> mycomparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if( o1.length() > o2.length() ){
                    return 1;
                }else{
                    return -1;
                }
            }
        };
        result = names
                .stream()
                .sorted(mycomparator)
                .collect(Collectors.toList());
        result.forEach(System.out::println);

        // peek(Consumer<T> action)
        System.out.println("----------peek(Consumer<T> action)----------");
        result = names.stream()
                .flatMap(name -> Stream.of(name.split("")))
                .peek(e -> System.out.println("用peek输出过程数据" + e))
                .collect(Collectors.toList());

        // count()：计算 Stream 中的元素数量。
        System.out.println("----------count()----------");
        long names_count = names.stream().flatMap(name -> Stream.of(name.split(""))).count();
        System.out.println(names_count);

    }
}
