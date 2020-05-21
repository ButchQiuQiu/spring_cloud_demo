# 简介
* 一个微服务的后端网关.实现代理路由过滤.
# 使用
1. 导入依赖
```xml
<!-- 导入Eureka依赖 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
<!-- Eureka的客户端依赖 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<!-- 导入Hystrix依赖 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```
2. 配置yaml,和普通的服务一样
```yaml
server:
  port: 9001
spring:
  application:
    name: spring_cloud_zuul_9001
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/
  instance: 
    # 监控显示id
    instance-id: zuul.com
    # 是否隐藏真实ip
    prefer-ip-address: true
```

3. 配置注解
```java
@SpringBootApplication
@EnableZuulProxy
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```
# 姿势
* 开启之后就可以代理访问了,也可以扩展访问域名.
* 还可以忽略过滤请求
```yaml
zuul:
  # 映射请求
  routes:
    demo.serviceId: demo
    demo.path: /d/**
  # 忽略请求
  ignored-services: demo
  # 隐藏所有的真实项目
  # ignored-services:"*" 
  # 统一前缀
  # prefix:/butch
```
  
  
