# Protocol 协议
```
Protocol Buffers Documentation: https://protobuf.dev/
Java Proto使用方法: https://github.com/protocolbuffers/protobuf/tree/main/java
```
使用:
`Maven`
如果您正在使用Maven，请使用以下内容:
```
<dependency>
  <groupId>com.google.protobuf</groupId>
  <artifactId>protobuf-java</artifactId>
  <version>3.23.0</version>
</dependency>
```

如果您想使用protobufJsonFormat等功能，请添加对protobufjavautil包的依赖项：

```
<dependency>
  <groupId>com.google.protobuf</groupId>
  <artifactId>protobuf-java-util</artifactId>
  <version>3.23.0</version>
</dependency>
```

从源代码构建-使用Maven
```
$ protoc --version
$ mvn test
$ mvn install
$ mvn package
```

proto2:
```https://protobuf.dev/programming-guides/proto2/```

proto3:
```https://protobuf.dev/programming-guides/proto3/```

## 核心概念、框架和生命周期
### 服务定义
gRPC 允许您定义四种服务方法：
