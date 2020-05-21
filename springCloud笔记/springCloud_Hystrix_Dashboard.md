# 简介
监控Hystrix页面,服务提供方必须有actuator依赖和配置提供监听.
# 配置
1. 导入Hystrix和Hystrixboard的依赖,openfeign中包含Hystrix依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```

2. 启动类中开启注解
```EnableHystrixDashboard```
 
3. 服务端也需要添加dashboard依赖才能被监控,并且需要注册一个servlet,需要调用有熔断机制(```@HystrixCommand```)的接口才能ping到数据.
```java
//注入被监控的servlet
@Bean
public ServletRegistrationBean hystrixMetricsStreamServlet(){
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
    servletRegistrationBean.addUrlMappings("/actuator/hystrix.stream");
    return servletRegistrationBean;
}
```