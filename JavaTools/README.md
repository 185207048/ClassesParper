# Java工具类汇总
集中介绍java中的工具类，比如比较器、计时器等
## 比较器
在 Java 中，比较器（Comparator）是一个接口，用于定义对象之间的排序规则。它可以用于对集合中的对象进行自定义排序。实现 Comparator 接口的类需要重写 compare 方法，该方法接受两个参数，分别是要比较的两个对象。
以下是关于 Java 比较器的基本介绍：
- Comparator 接口位于 java.util 包中。
- Comparator 接口中的主要方法是 int compare(T o1, T o2)，其中 T 是要比较的对象的类型。compare 方法返回一个整数，表示两个对象的相对顺序：
如果返回值小于 0，则表示 o1 应该排在 o2 之前。
如果返回值等于 0，则表示 o1 和 o2 相等，它们的顺序无关紧要。
如果返回值大于 0，则表示 o1 应该排在 o2 之后。
- 在使用比较器时，可以利用 Java 8 中引入的 lambda 表达式简化代码。

Java 8 为 Comparator 接口引入了许多新的默认方法和静态方法，以支持更加灵活和简洁的比较器创建。以下是一些常用的方法：

- thenComparing(Comparator<? super T> other)：链式地组合另一个比较器，当两个对象在当前比较器下相等时使用。
- comparing(Function<? super T, ? extends U> keyExtractor)：使用给定的函数生成一个比较器，根据提取的键对对象进行比较。
- comparingInt(ToIntFunction<? super T> keyExtractor)：使用给定的函数生成一个比较器，根据提取的 int 键对对象进行比较。
- nullsFirst(Comparator<? super T> comparator)：生成一个新的比较器，首先将 null 值视为最小值，然后使用给定的比较器。
- nullsLast(Comparator<? super T> comparator)：生成一个新的比较器，首先将 null 值视为最大值，然后使用给定的比较器。
  

Comparator 接口没有直接的实现类，因为它是一个函数式接口，旨在通过 Lambda 表达式、方法引用或匿名内部类进行实现。然而，Comparator 本身提供了许多实用的静态方法，以简化比较器的创建。这些方法可以看作是 "实现" 了 Comparator 功能的工具方法。

# 消费者工具
Consumer 是 Java 中的一个函数式接口，表示接受一个参数并对其执行某些操作，但不返回结果。这个接口在 java.util.function 包中，用于表示那些只对输入参数执行某些操作，不关心返回值的函数。Consumer 接口在 Java 8 引入，与其他函数式接口一起，为 Java 引入了更加灵活和简洁的编程风格。
- Consumer 接口定义了一个抽象方法 accept(T t)，接受一个泛型参数。
- Consumer 接口还包含一些默认方法，例如 andThen(Consumer<? super T> after)，该方法允许链接多个 Consumer 实例以便顺序执行。 
  
Consumer 接口可以用于以下场景：
- 遍历集合并对每个元素执行操作:
  使用 forEach 方法，可以将 Consumer 作为参数传递。这可以使遍历和操作更简洁和清晰。
- 资源管理:
  当你需要在处理资源（如文件、数据库连接等）时执行某些操作，但不需要返回结果时，可以使用 Consumer。例如，你可以使用 Consumer 对文件的每一行执行操作。
- 事件处理:
  在事件驱动的应用程序中，可以使用 Consumer 作为事件处理器。当某个事件触发时，可以调用 accept() 方法执行相关操作。
- 函数组合:
  使用 Consumer 的 andThen() 方法，可以组合多个操作，形成一个新的操作。这样可以组织和复用代码，提高可读性。
