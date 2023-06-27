# Java8Stream模块
注：语雀Spring - (Spring框架解析 -- Spring-boot.jar -- Springboot的Context与listeners)  

本模块用于展示Java 8的新特性，也就是Stream处理方式，Stream处理不同于常见的
List、Map、Collection等容器，它存在以下5个特点。  
- 非集合：不会保存数据。
- 流水线处理：数据会以Stream的形式进行方法传递。
- 惰性求值：只有在需要时才会计算结果。
- 函数式编程：将函数（lambda表达式）作为函数传递下去。
- 并行处理：可以利用多核处理器进行计算

## 文件介绍:
- MyStream：展示java8的Stream的使用，一个简单的例子
- WhatStream：展示Stream都能做什么  
  + filter(Predicate<T> predicate)：根据提供的条件（谓词函数）筛选 Stream 中的元素。
  map(Function<T, R> mapper)：将 Stream 中的每个元素转换为另一种类型或值。
  + flatMap(Function<T, Stream<R>> mapper)：将每个元素转换为一个新的 Stream，然后将这些 Stream 连接成一个新的 Stream。它接受一个函数作为参数，这个函数接受一个类型为 T 的输入并返回一个类型为 R 的 Stream。通常，flatMap 用于处理嵌套的数据结构，例如将 Stream<Stream<T>> 转换为 Stream<T>。
  + distinct()：移除 Stream 中的重复元素。
  + sorted()：对 Stream 中的元素进行自然排序。
  + sorted(Comparator<T> comparator)：根据提供的比较器对 Stream 中的元素进行排序。
  + peek(Consumer<T> action)：对 Stream 中的每个元素执行一个操作，通常用于调试。
  终止操作会返回一个结果或执行一个副作用，这通常是对 Stream 中的元素进行聚合、搜索或其他处理。以下是一些常用的终止操作：
  + toArray()：将 Stream 转换为数组。
  + collect(Collector<T, A, R> collector)：将 Stream 中的元素收集到一个集合中，如 List、Set 或 Map。
  + count()：计算 Stream 中的元素数量。
  + forEach(Consumer<T> action)：对 Stream 中的每个元素执行一个操作。
  + reduce(BinaryOperator<T> accumulator)：使用给定的累加器将 Stream 中的元素合并成一个结果。
  + min(Comparator<T> comparator) 和 max(Comparator<T> comparator)：根据提供的比较器找到 Stream 中的最小值和最大值。
  + findFirst() 和 findAny()：查找 Stream 中的第一个元素或任意元素（通常用于并行流）。
  + anyMatch(Predicate<T> predicate)，allMatch(Predicate<T> predicate) 和 noneMatch(Predicate<T> predicate)：检查 Stream 中的元素是否满足给定的条件。
- StreamUseByContainer：证明Stream只能在容器类实例化的实体下使用，并分析他的设计模式
  ![img_1.png](img_1.png)
  这个函数的返回类型是Stream，他的结构图如下图所示，他继承了BaseStream
  ![img.png](img.png)
  ![img_2.png](img_2.png)
  ![img_3.png](img_3.png)
  如上图事例所示点击stream()函数后会跳转到Collection接口，然后调用下面函数
  ![img_4.png](img_4.png)
  再点击StreamSupport.stream()函数，会进入到StreamSupport类
  注释中对这个类的描述是"这个类主要是为库编写人员提供数据结构的流视图,面向最终用户的大多数静态流方法都在各种stream类中。"
  其中数据流视图是一种可以高效处理数据的结构，它允许开发者编写代码以声明性的方式对数据进行转换、过滤和其他操作。
  然后利用了数据流视图+管道设计模式的作用下让使用者将主要与各种具体的Stream类进行交互，这些类提供了静态方法，允许用户创建和操
  作数据流。这些方法通常设计得很直观，易于理解和使用，这样用户可以快速地进行开发，而不需要深入了解库的内部实现。
  ![img_5.png](img_5.png)
  然后这里返回了一个ReferencePipeline的元素，这个元素由于三部分组成，分别是Head、StatefulOp和StatelessOp，在创建类的时候，只用传入他的Head就可以了。
  当然除了ReferencePipeline元素以外，还有其他的类型，如下图所示。
  ![img_6.png](img_6.png)
  所以到目前位置就要掌握两个Java8函数编程的知识点：
  
  1、返回元素的结构和每个部分的作用
  
  2、管道设计模式的结果和作用
  
  3、管道设计模式的使用
- ByLazyResult：展示惰性求值
- Lambada：展示函数式编程
- Parallel：展示多线程运行

