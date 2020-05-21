# 概念
* 基于springboot的分布式架构,结构简单.
* 目前Netflix全家桶较为完善
## 基础
* 一般为一个maven父工程和若干个子子模块,每个模块都能独立运行,和dubbo体系的不同是每个模块都只用json交互.
低耦合高内聚,只要是标准的json通讯那么不同语言如python .net go也能相互调用.
* springCloud自带的RestTemplate可以模拟ajax请求和回调,此类需要自己到javaconfig中配置.
## Euraka
* NetFlix开发的一个服务注册与发现的核心模块,功能类似dubbo zookeeper,比dubbo体系使用更加简洁.
* 通过一定时间的检测测试provider服务是否存在,简称心跳检测.
### 三大角色
* Euraka:注册与发现
* provider:服务提供者
* consumer:服务消费者

## 其他
* Eureka会默认开启保护机制,服务挂掉后也会保持服务注册表中的信息,不会注销任何微服务,当这个服务重新连接后才会退出保护模式,
```server.enable-self-preservation```为false就会关闭保护机制,无法维持心跳的服务就会被注销掉.
* 可以添加actuator完善client的info信息,一般用于开发时给其他同事的信息
* 可以装配DiscoveryClient来获取到其他服务的info信息
* 
### eureka集群
* server:defaultZone中设置其他需要关联的EurekaServer.
* client:defaultZone中需要设置所有需要挂载的server.
* 不关闭fetch-registry就代表开启了集群模式,instance的hostname代表自己的地址,defaultZone中存放其他Eureka服务的地址逗号隔开.
```yaml
server:
  port: 7001
#Eureka配置
eureka:
  instance:
    #Eureka服务端的实例名称
    hostname: eureka-server1
  client:
    # 检索服务选项，当设置为True(默认值)时，会进行服务检索,注册中心不负责检索服务
    register-with-eureka: false
    # 服务注册中心也会将自己作为客户端来尝试注册自己,为true（默认）时自动生效
    fetch-registry: false
    #注册地址,监控是http://${eureka.instance.hostname}:${server.port}
    service-url:
      # defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      defaultZone: http://eureka-server1:7001/eureka/,http://eureka-server2:7002/eureka/
```

# 配置

# server服务端
1. 使用父模块的springCloud版本管理,导入依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
</dependencies>
```
2. 配置yaml
```yaml
server:
  port: 7001
#Eureka配置
eureka:
  instance:
    #Eureka服务端的实例名称
    hostname: localhost 
  client:
    #表示是否向注册中心注册自己
    register-with-eureka: false
    #false代表自己是注册中心
    fetch-registry: false
    #注册地址,监控是http://${eureka.instance.hostname}:${server.port}
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
3. 开启注解@EnableEurekaServer
```java
@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); 
	}

}
```
# provider提供者
## 配置
1. 导入依赖,和server一样版本都可以使用父工程统一管理
```xml
<!-- 导入Eureka依赖 -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
2. 配置yaml
* provider中的yaml设置应该和server中的设置对接,比如注册地址应该和server中的地址对应
```yaml
eureka:
  client:
    service-url:
      #服务注册地址,对应eureka的服务注册url
      defaultZone: localhost:7001/eureka/
```
3. 开启注解@EnableEurekaClient
```java
@SpringBootApplication
@EnableEurekaClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```

# consumer消费者
* provider提供者流程一样,只需要在yaml中设置```eureka.client.register-with-eureka=false```就能表示这是个消费者不向eurekaServer注册服务,只消费.
