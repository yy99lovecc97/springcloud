package com.lovecc.userservice.service;

import com.lovecc.common.dto.RespDTO;
import com.lovecc.common.exception.CommonException;
import com.lovecc.common.exception.ErrorCode;
import com.lovecc.userservice.client.AuthServiceClient;
import com.lovecc.userservice.dao.UserDao;
import com.lovecc.userservice.domain.JWT;
import com.lovecc.userservice.domain.User;
import com.lovecc.userservice.dto.LoginDTO;
import com.lovecc.userservice.util.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    AuthServiceClient authServiceClient;

    public User createUser(User user){
        return userDao.save(user);
    }
    public User getUserInfo(String username){
        return userDao.findByUsername(username);
    }
    public RespDTO login(String username,String password){
        User user =userDao.findByUsername(username);
        if (user == null){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        //差一个感叹号
        if (!BPwdEncoderUtils.matches(password,user.getPassword())){
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }
        JWT jwt = authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjExNzg4MQ==","password",username,password);
        if (jwt == null){
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        return RespDTO.onSuc(loginDTO);
    }
}
