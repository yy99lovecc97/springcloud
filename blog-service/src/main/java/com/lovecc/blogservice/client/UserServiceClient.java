package com.lovecc.blogservice.client;

import com.lovecc.blogservice.client.hystrix.UserServiceHystrix;
import com.lovecc.blogservice.domain.User;
import com.lovecc.common.dto.RespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service",fallback = UserServiceHystrix.class)
public interface UserServiceClient {
    @PostMapping(value = "/user/{username}")
    RespDTO<User> getUser(@RequestHeader(value = "Authorization")String token, @PathVariable("username")String username);
}
