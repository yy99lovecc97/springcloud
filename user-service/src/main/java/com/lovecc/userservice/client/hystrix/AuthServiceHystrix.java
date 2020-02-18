package com.lovecc.userservice.client.hystrix;

import com.lovecc.userservice.client.AuthServiceClient;
import com.lovecc.userservice.domain.JWT;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        System.out.println("--------opps getToken hystrix---------");
        return null;
    }
}
