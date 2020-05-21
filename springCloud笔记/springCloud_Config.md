# 简介
* 把所有spring的模块配置如yaml丢到git远程仓库集中管理的模块.
* 此模块是cs架构,需要Client客户端和Server服务端.

# 使用
## 服务端
1. 导入依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
2. 配置yaml并开启注解```@EnableConfigServer```
```yaml
server:
  port: 10001
spring:
  application:
    name: config
  cloud:
    config:
      server:
        git:
          uri: https://github.com/ButchQiuQiu/spring_cloud_demo.git
          # 公开仓库可以不填账号密码
        # username:
        # password: 
          # 配置文件分支
          default-label: master 
          # 配置文件所在根目录
          search-paths: config
eureka:
  client:
    service-url:
      #服务注册地址,对应eureka的服务注册url
      defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/  
```

## 客户端
* 理论上所有其他模块所有配置都应该去Config服务端获取服务.
1. 导入依赖.
```xml
<!-- 导入config客户端依赖 -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-config-client</artifactId>
</dependency>
```
2. yaml配置,一般配置为bootstrap,优先级会比application高.
```bootstrap```
```yaml

```
