#Spring源码解读
对spring的认知：spring是一个基础的框架，同时他为我们提供了容器来装在基础的bean对象，之前在使用对象的时候需要去new，现在我只需要告诉框架我都用了那些对象，他会帮我们创建好，并且帮我们维护整体的生命周期，而且在spring基础之上还有springboot，springcloud良好的扩展技术栈，这些框架都是以spring框架为基石的
spring包括ioc,aop,ioc如何实现，生命周期，循环依赖

### Spring加载注解和配置文件
Spring可以通过容器获取类，所以里面是由于好多以K-V形式的map组成的，当获取获取一个类的时候

配置可以通过注解和配置文件的方式将类加载进堆中

![img_1.png](img_1.png)
如上图所示，从xml文件中读取类和从注解的形式中读取类，分别利用的两种方法。
读取后将类装载到容器中，这里的容器有一个map结构，这个map结构即使BeanDefinition,
用于Bean的定义信息,如下图所示，这是springframework中的源码，通过下面setBeanClassName等
方法的设置可以读取类的信息
![img_2.png](img_2.png)
![img_3.png](img_3.png)

### Spring源码的扩展性
在读取xml和注解的时候需要有一个接口来约束规范，这个接口就是BeanDefinitionReader,通过这个接口
可以通过这个接口对定义和读取bean的方法进行扩展。
注：接口与抽象类的区别（接口是自上而下的，抽象类是自上向下的）

接口扩展：BeanFactoryPostProcessor

反射基础代码:
```
1、获取Class对象

Class clazz = Class.forName("包名+类名");

Class clazz = 对象.getClass();

Class clazz = 类名.class;

2、获取构造器

Constructor ctor = clazz.getDeclaredConstructor();

3、创建对象

Object obj = ctor.newInstance();
```
Spring 模块介绍

framework-bom："Bill of Materials" - 一个特殊的 pom，可以让你在依赖管理中不必为每个单独的版本指定版本号。

framework-docs：包含所有的文档，例如参考手册，Javadoc 等。

<span style="color:red;">framework-platform</span>：提供了构建 Spring 应用程序的基础。

gradle：包含构建 Spring 框架所需的所有 Gradle 插件和脚本。

integration-tests：包含集成测试。

<span style="color:red;">spring-aop</span>：提供了面向切面编程的功能。

<span style="color:red;">spring-aspects</span>：包含了一些可以与 AspectJ 集成的类。

<span style="color:red;">spring-beans</span>：提供了创建、读取和管理 bean 的功能。

<span style="color:red;">spring-context</span>：为其他模块提供运行时环境。提供了框架式的事件处理、国际化和资源加载。

<span style="color:red;">spring-context-indexer</span>：为 Spring 提供了一种快速扫描组件的方法。

<span style="color:red;">spring-context-support</span>：为整个应用程序上下文提供支持。

<span style="color:red;">spring-core</span>：Spring Framework 的核心，包括 IoC 和 DI 功能。

spring-core-test：提供测试 Spring 应用程序所需的类和方法。

<span style="color:red;">spring-expression</span>：支持运行时查询和操作对象图，包含了 Spring 表达式语言 (SpEL) 的功能。

<span style="color:red;">spring-instrument</span>：提供了类检测和类转换的功能，通常用于性能监控。

<span style="color:red;">spring-jcl</span>：Spring 对 Commons Logging 的一个简单封装，用于日志管理。

<span style="color:red;">spring-jdbc</span>：提供了使用 JDBC 访问数据库的功能。

<span style="color:red;">spring-jms</span>：提供了使用 Java Message Service (JMS) 的功能。

<span style="color:red;">spring-messaging</span>：提供了消息驱动应用程序的基础设施，包括用于消息系统的简单的样板代码。

<span style="color:red;">spring-orm</span>：提供了对对象关系映射（ORM）工具的支持，如 JPA 和 Hibernate。

<span style="color:red;">spring-oxm</span>：提供了对象/XML 映射的抽象层。

<span style="color:red;">spring-r2dbc</span>：提供了对反应式 SQL 数据库连接的抽象层。

spring-test：提供了对 Spring 组件的集成测试的支持。

<span style="color:red;">spring-tx</span>：提供了编程式和声明式的事务管理。

<span style="color:red;">spring-web</span>：为基于 web 的应用程序提供基础设施，包括多文件上传功能。

<span style="color:red;">spring-webflux</span>：用于构建响应式 web 应用程序的模块。

<span style="color:red;">spring-webmvc</span>：包含 Spring 的模型视图控制器 (MVC) 和 REST Web 服务实现。

<span style="color:red;">spring-websocket</span>：提供了 WebSocket 功能，包括对 WebSocket 消息的发送和接收。


### Spring官方文档
- Spring Framework
  
  第一部分
   - Core Technologies
    
        这部分主要是讲解SpringFramework中的核心功能。
    
    1、IOC（Inversion of Control）
    2、AOP（Spring’s Aspect-Oriented Programming）
    3、AspectJ 的主要特性
    4、AOT与GraalVM
    
