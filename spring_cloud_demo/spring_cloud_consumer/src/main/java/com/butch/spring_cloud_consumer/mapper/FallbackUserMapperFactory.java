package com.butch.spring_cloud_consumer.mapper;

import com.butch.spring_cloud_api.pojo.User;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * FallbackUserMapperFactory
 * 缺省的UserMapper
 */
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