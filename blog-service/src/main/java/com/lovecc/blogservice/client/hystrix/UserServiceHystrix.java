package com.lovecc.blogservice.client.hystrix;

import com.lovecc.blogservice.client.UserServiceClient;
import com.lovecc.blogservice.domain.User;
import com.lovecc.common.dto.RespDTO;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements UserServiceClient {
    @Override
    public RespDTO<User> getUser(String token, String username) {
        System.out.println(token);
        System.out.println(username);
        return null;
    }
}
