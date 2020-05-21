package com.butch.spring_cloud_consumer.mapper;

import com.butch.spring_cloud_api.pojo.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 动态代理mapper,把其方法挂载在远程服务上
 */
//开启Feign并指定服务名
@FeignClient(value = "DEMO",fallbackFactory = FallbackUserMapperFactory.class)
public interface UserMapper {
    @GetMapping("/provider/user")
    public User getUser();
}