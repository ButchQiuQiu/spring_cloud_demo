# 简介
* springCloudRibbon是一个*客户端*负载均衡的模块.
* 集成在消费者模块上,调用的服务直接变成了服务名(spring.application.name),由Ribbon来实现路由和负载均衡.


# 使用
1. 导入依赖,Ribbon需要EurekaClient依赖,版本最好由总工程统一管理.
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
2. 配置yaml,需要和Eureka消费者配合使用,所以包含Eureka消费者的配置.
```yaml
eureka:
  client:
    # 不向eureka注册自己
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka-server2:7002/eureka/,http://eureka-server1:7001/eureka/****
```
3. 需要配置javaConfig配置Ribbon,只需要在注入RestTemplate上加入负载均衡的注解就能实现负载均衡.
```java
@Bean
@LoadBalanced
public RestTemplate getRestTemplate(){
    return new RestTemplate();
}
```
4. 消费者需要服务的ip(host:port)变成了那个服务的虚拟ip即(spring.application.name).全部转为大写.
```java
@Autowired
private RestTemplate restTemplate;
private static final String REST_URL_PREFIX="http://DEMO";
@GetMapping("/consumer/user")
public String getUser() throws RestClientException, JsonProcessingException{
    ObjectMapper objectMapper = new ObjectMapper();
    
    return objectMapper.writeValueAsString(restTemplate.getForObject(REST_URL_PREFIX+"/provider/user",User.class));
}
```
# 负载均衡
1. 只要注入实现IRule接口的类,就能使用这个负载均衡策略.
```java
//随机负载均衡
@Bean
public IRule myRule(){
    return new RandomRule();
}
```
2. 当然也可以自定义负载均衡策略,只要实现IRule并且注入即可.
也可以指定服务对应的负载均衡,需要在启动类上注解```@RibbonClient(name="服务名",configuration=config类.class)```,注意这个config不能被springBoot默认扫描到.