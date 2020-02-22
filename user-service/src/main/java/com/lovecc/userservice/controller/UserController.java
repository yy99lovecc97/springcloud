package com.lovecc.userservice.controller;

import com.lovecc.common.annotation.SysLogger;
import com.lovecc.common.dto.RespDTO;
import com.lovecc.userservice.domain.User;
import com.lovecc.userservice.service.UserService;
import com.lovecc.userservice.util.BPwdEncoderUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的);
     * GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。
     * @param user
     * @return
     */
    @ApiOperation(value = "注册",notes = "username和password为必选项")
    @PostMapping("/registry")
    @SysLogger("registry")
    public User createUser(@RequestBody User user){
        System.err.println(user.getPassword());
        String encoderPassword = BPwdEncoderUtils.BCryptPassword(user.getPassword());
        user.setPassword(encoderPassword);
        return userService.createUser(user);
    }
    @ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
    @PostMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    @SysLogger("getUserInfo")
    public RespDTO getUserInfo(@PathVariable("username") String username){
        //参数判读省略
        User user=  userService.getUserInfo(username);
        return RespDTO.onSuc(user);
    }
    @ApiOperation(value = "登录", notes = "username和password为必选项")
    @PostMapping("/login")
    @SysLogger("login")
    public RespDTO login(@RequestParam String username , @RequestParam String password){
        //参数判读省略
        return   userService.login(username,password);
    }
}
