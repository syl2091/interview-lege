package com.lege.model.dto;

import lombok.Data;

@Data
public class LoginReq {

    private String name;
    private String password;

    private String phone;
    private String validateCode;//手机验证码

    private String wxCode;//用于微信登录
    /**
     * account : 用户名密码登录
     * sms : 手机验证码登录
     * we_chat : 微信登录
     */
    private String type;
}
