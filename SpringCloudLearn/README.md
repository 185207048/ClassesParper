# SpringCloud学习
## Gateway网关

```
https://spring.io/projects/spring-cloud-gateway
```

### After Route Predicate 工厂
After Predicate工厂使用datetime字段匹配在指定日期时间之后发生的请求
```
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: https://example.org
        predicates:
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
```
上面这个例子就是此路径匹配所有在2017年1月2日之后所有的请求。</br>
当然也有Before Predicate工厂，和After工厂的使用方法是一样的。</br>
也可以通过Between来匹配两个时间之中的请求。</br>
```
predicates:
 - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
```
### Cookie Predicate工厂
也可以匹配Cookie Predicate工厂，此谓词匹配具有给定名称且其值与正则表达式匹配的 Cookie。
```
- Cookie=chocolate, ch.p
```

### Header Predicate工厂
```
- Header=X-Request-Id, \d+
```

### Host Route Predicate工厂
```
- Host=**.somehost.org,**.anotherhost.org
```
此功能还支持URI 模板变量，如{sub}.myhost.org
此谓词提取 URI 模板变量（如前面示例中定义的 sub）作为名称和值的映射，并使用 ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE 中定义的键将其放置在 ServerWebExchange.getAttributes() 中。然后，这些值可供GatewayFilter工厂使用

### Method Route Predicate工厂
```
- Method=GET,POST
```

# 代码示例
```
SpringCloudLearn的Order是订单服务
```