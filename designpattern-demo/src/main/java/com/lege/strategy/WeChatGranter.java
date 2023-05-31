package com.lege.strategy;

import com.lege.model.dto.LoginReq;
import com.lege.model.vo.LoginResp;
import org.springframework.stereotype.Component;

/**
 * 策略:微信登录
 */
 @Component
public class WeChatGranter implements UserGranter{

	@Override
	public LoginResp login(LoginReq loginReq)  {
		System.out.println("策略:登录方式为微信登录");
		// TODO
		// 执行业务操作
		
		return new LoginResp();
	}
}
