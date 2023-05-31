package com.lege.controller;


import com.lege.model.dto.LoginReq;
import com.lege.model.vo.LoginResp;
import com.lege.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResp login(@RequestBody LoginReq loginReq) throws InterruptedException {
        if(loginReq.getType().equals("abc")){
            log.error("没有这种登录方式:{}",loginReq.getType());
        }
        if(loginReq.getType().equals("123")){
            throw new RuntimeException("错误的登录方式");
        }

        return userService.login(loginReq);
    }
}
