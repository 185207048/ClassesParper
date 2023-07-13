# Proto2协议学习
## 设置一个Message Type
```
syntax = "proto2";

message SearchRequest {
  optional string query = 1;
  optional int32 page_number = 2;
  optional int32 result_per_page = 3;
}
```
上面这个就是设置了一个传输规则，规则中包含了两个int32,一个string类型的传输类型
```
...
option java_package = "com.example.grpc.api";
option java_outer_classname = "RPCDateServiceApi";
option java_multiple_files = true;
...
```
上面三行则是设置了java_package、java_outer_classname和java_multiple_files
设置这三行可以通过protoc命令生成代码。

## 类型
类型是传输规则的重要组成部分，proto都是使用scalar类型进行命名：

|.proto 类型|描述|对应的Java Type|
| :--------:  | :-----:  | :----:  |
|double| |double|
|float| |float|
|int32|使用可变长度编码。编码负数效率低下 - 如果您的字段可能有负值，请改用 sint32。|int|
|int64|使用可变长度编码。编码负数效率低下 - 如果您的字段可能有负值，请改用 sint64。|long|
|uint32|使用可变长度编码。|int|
|uint64|使用可变长度编码。|long|
|sint32|使用可变长度编码。有符号 int 值。这些比常规 int32 更有效地编码负数。|int|
|sint64|使用可变长度编码。有符号 int 值。这些比常规 int64 更有效地编码负数。|long|
|fixed32|比 uint32 更高效|int|
|fixed64|比 uint64 更高效|long|
|sfixed32|始终包含4byte|int|
|sfixed64|始终包含8byte|long|
|bool| |boolean|
|string|字符串必须始终包含 UTF-8 编码文本。|String|
|bytes|可以包含任意任意字节序列。|ByteString|

这里也可以定义枚举类型和构造类型在组成部分中。

## 分配字段编号
每个组成中的字段需要指定一个介于 1 到 536,870,911 之间的数字，
并且必须是唯一的，字段号不能为 19,000 到 19,999，
不能使用之前的 reserved field numbers和任何已经被扩展分配的领域数字。

## 指定字段规则
`optional`：格式良好的消息可以有零个或一个该字段（但不能超过一个）。

`repeated`: 此字段可以在格式良好的消息中重复任意次数（包括零）。重复值的顺序将被保留。

`required`: 所需的字段非常有问题，它们被从proto3中删除了。

由于历史原因，标量数字类型（例如 int32、int64、enum）的重复字段无法高效编码。新代码应使用特殊选项 [packed = true] 以获得更有效的编码。例如：
```
repeated int32 samples = 4 [packed = true];
repeated ProtoEnum results = 5 [packed = true];
```
## 导入定义
```
import "myproject/other_protos.proto";
```
通过导入定义可以引用其他proto文件中的messge Type

## 嵌套类型
```
message SearchResponse {
  message Result {
    optional string url = 1;
    optional string title = 2;
    repeated string snippets = 3;
  }
  repeated Result result = 1;
}
```

## 类型扩展
这个特性允许你在不修改原始定义的情况下，向消息类型动态添加新的字段。

这种扩展机制的主要用途是提供了一种对已有消息进行扩展的方式，而不会破坏消息的向后兼容性。这对于构建可扩展的、能够持久存储的数据系统来说非常重要。你可以添加新的字段，而不会影响旧的代码读取旧的消息。

容器消息的.proto文件将具有较少的导入/依赖项。这可以改善构建时间，`打破循环依赖关系`，并在其他方面促进松耦合。扩展对此非常好。

允许系统以最小的`依赖性`和`协调性`将数据附加到容器消息。
```
import "common/user_profile.proto";

package puppies;

message Photo {
  extend common.UserProfile {
    optional int32 likes_count = 111;
  }
  ...
} 
```

## 定义扩展范围
## Any字段

#Proto3协议学习
Proto3与Proto2之间的区别

1. 字段默认值：

在 proto2 中，可以为字段定义默认值，且可以检查一个字段是否被设置。

在 proto3 中，所有字段都有默认值（例如，数字类型的默认值为 0，字符串类型的默认值为空字符串），并且无法检查一个字段是否被设置。

2. 字段标签（required、optional、repeated）：

在 proto2 中，字段可以被标记为 required、optional 或 repeated。

在 proto3 中，移除了 required 和 optional 标签，只保留了 repeated。这意味着所有字段都是可选的，且可以重复。

3. 扩展（Extensions）：

在 proto2 中，提供了一种名为 "extensions" 的特性，允许向消息动态添加新的字段，而无需修改原始定义。

在 proto3 中，移除了扩展特性。取而代之的是，引入了一种新的消息类型 Any，可以用来存储任意类型的消息。

4. 新的特性：

proto3 引入了一些新的特性，例如 map 字段用于表示键值对，以及 oneof 关键字用于表示一个字段可以是多种类型中的一种。

一旦指定了数据结构，就可以使用协议缓冲区编译器proto根据proto定义以首选语言生成数据访问类。它们为每个字段提供了简单的访问器，如name（）和set_name（），以及将整个结构序列化到原始字节/从原始字节解析整个结构的方法。因此，例如，如果您选择的语言是C++，那么在上面的示例中运行编译器将生成一个名为Person的类。然后，您可以在应用程序中使用此类来填充、序列化和检索Person协议缓冲区消息。