# 简介
* 一个服务容灾的模块,主要防止微服务链上的某个服务宕机所造成的连锁雪崩.
  
# 使用
1. 导入依赖,属于springCloud大家族可以父工程集中管理版本
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```
## 熔断
1. 在启动类中开启Hystrix的熔断注解,获得支持.用于被调用方,即下游.
```java
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```
2. 在调用服务的方法上启动熔断注解@HystrixCommand,如果服务调用失败就会启动注解的策略.
* 比如回滚,调用失败或者异常就会直接调用自定义的回滚函数.
```java
@HystrixCommand(fallbackMethod = "fallbackUser")
@GetMapping("/provider/null")
public User getFallbackUser(){
    throw new RuntimeException();
}

public User fallbackUser(){
    return new User("fallback","fallback","fallback");
}
```
## 服务降级
* 可以关闭或者调用默认处理配合feign使用.用于调用方,上游对下游的服务降级.
1. 配置
```yaml
# 开启服务降级
feign:
  hystrix:
    enabled: true
```
2. 在远程动态代理的feign上可以设置缺省的实例.
```java
//开启Feign并指定服务名
@FeignClient(value = "DEMO",fallbackFactory = FallbackUserMapperFactory.class)
public interface UserMapper {
    @GetMapping("/provider/user")
    public User getUser();
}
```
3. 然后自定义fallback的实例
```java
@Component
public class FallbackUserMapperFactory  implements FallbackFactory<UserMapper>{

    @Override
    public UserMapper create(Throwable arg0) {
        return new UserMapper(){
        
            @Override
            public User getUser() {
                return new User("服务降级", "服务降级", "服务降级");
            }
        };
    }

}
```