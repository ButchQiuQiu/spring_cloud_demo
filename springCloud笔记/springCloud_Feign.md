# 简介
* 底层使用动态代理,自动包装计算注入指定服务的请求请求的文本返回值.

# 使用
1. 导入maven依赖,需要和ribbon和Eureka配合使用,当然也可以配合其他注册中心使用.
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
2. 启动类开启```@EnableFeignClients```注解后,注入装配常规springBoot使用法.
  ---
```java
/**
 * 动态代理mapper,把其方法挂载在远程服务上
 */
//开启Feign并指定服务名
@FeignClient(value = "DEMO")
public interface UserMapper {
    @GetMapping("/provider/user")
    public User getUser();
}
```
---
```java
@Autowired
private UserMapper userMapper;
@GetMapping("/consumer/feign")
public User feign(){
    return userMapper.getUser();
}
```