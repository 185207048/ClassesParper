# ClassesParper
用于讲课课件
# 模块介绍
 `Java 基础模块`
 - Java8Stream: Java 8 函数式编程的应用
 - JavaTools: Java中常见工具类
 - NetWorkDemo: Java网络模块应用
 - ThreadDemo: Java线程应用
 - NormalModule: Java常用基础类
 - TypeAndContainer: Java泛型与容器
 - ListAndQueue: Java中列表与队列
 - MapAndSet: Java中的Map和set
 - ReflectModule: Java反射
 - StackAndQuery: 堆与优先级
 - NormalContainer: Java中的通用容器类
 - FileWithStram： Java中文件应用
 - SynchronizedAndThead: 并发与并发包
 - AutomAndCas: Java原子性与CAS
 - SynchronizedAndContainer: 并发容器
 - SynchronizedAndAsync: 并发与异步
 - SynchronousAndAsync: 并发与同步
 - Annotated: Java中的注解
 - TrendsProxy: Java中的动态代理
 - ClassLoader: Java类的加载机制
 - FuncCode: 函数式编程
 - JavaAOP: 讲解Java中的AOP编程
---- 

通信方式：

`webSocket`

`NIO`

`Netty`

`Grpc`

```
参考博客
 https://blog.csdn.net/an_gentle_killer/article/details/123282801
 http://doc.oschina.net/grpc
```
gRPC（Google Remote Procedure Call）是一种高性能、开源和通用的 RPC（远程过程调用）框架，Google 公司发起并开源。gRPC 的目标是使各种环境中的服务能够相互通信。gRPC 提供了一个简单的方法，让客户端应用可以直接调用另一台服务器上的方法，就好像是本地对象的方法一样。

以下是 gRPC 出现的原因和主要特性：

1.多语言支持：gRPC 支持多种语言，如 Go、Python、Java、C++ 等，使开发者可以在他们熟悉的编程环境中开发应用程序。

2.性能：gRPC 基于 HTTP/2，支持双向流、流控制、头部压缩、多路复用等特性。相比于基于 HTTP/1.1 的 RESTful API，gRPC 有更高的性能和更低的延迟。

3.编码效率：gRPC 默认使用 Protocol Buffers 作为接口定义语言，该语言能够自动生成客户端和服务器代码。相比 JSON 和 XML，Protocol Buffers 的编码和解码效率更高，数据更小。

4.连续性和可扩展性：在分布式系统中，gRPC 能够支持客户端和服务器之间的稳定、可靠和低延迟的双向通信。同时，gRPC 也支持连接数的扩展。

5.强大的生态系统：由于是 Google 的开源项目，gRPC 享有强大的生态系统和社区支持，这对于项目的持续改进和问题的解决具有很大的帮助。

因此，gRPC 的出现主要是为了满足现代分布式系统中的高效、跨语言、稳定和可扩展的通信需求。








`Netty + proto3`


----

 `ASM`

 `JVM`

 `KVM`

----

 `设计模式`
 
 `Spring基础`

 `SpringCloud`
 
 `正则表达式`

----

 `数据库`

----
 `计算机操作系统`
 
 `计算机组成原理`
 
 `数据结构`
 
 `计算机网络`
 
 `汇编语言`

----

 `C语言`
 
 `CPU与计算机系统自制`
 
 `k8s`
 
 `Docker与Virsh`
 
 `Windows与Linux`

----

 `DevOps与DevSecOps`

----

 `算法`
- Algorithm: 例题集合
 分类
  - 逻辑推理题
    Restructure2rowbinary
    
# 如何读源码
开始阅读代码：在开始阅读Spring AOT模块的代码之前，对Spring框架的设计和工作原理有一定的了解会很有帮助。如果你对Spring框架不熟悉，你可能需要先阅读一些关于Spring框架基础的教程和文档。如果你已经对Spring框架有了足够的了解，你可以直接开始阅读org.springframework.aot模块的源代码。你可以在GitHub上找到Spring Native项目的源代码：https://github.com/spring-projects-experimental/spring-native

阅读项目文档：在开始阅读源代码之前，通常先阅读项目的官方文档会有所帮助。Spring Native项目的官方文档提供了对项目目标和设计决策的高级概述，以及对如何使用项目的详细指南。你可以在这里找到Spring Native项目的官方文档：https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/

关注主要的类和接口：在阅读org.springframework.aot模块的源代码时，你可能需要关注一些主要的类和接口。例如，BootstrapCodeGenerator类是生成Spring应用程序的AOT引导代码的主要类。其他重要的类和接口可能包括AotOptions（控制AOT生成的选项），TypeSystem（提供对Java类型系统的访问），以及各种CodeContributor接口的实现（用于生成特定部分的AOT代码）。

查看测试用例：测试用例是了解模块如何工作的好资源。通过阅读和理解测试用例，你可以了解到模块的各个部分如何在一起工作，以及模块应该如何使用。你可以在org.springframework.aot模块的src/test目录下找到测试用例。


 