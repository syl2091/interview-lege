package com.lege.strategy;

import com.lege.model.dto.LoginReq;
import com.lege.model.vo.LoginResp;

import java.util.List;
import java.util.Map;

/**
 * 抽象策略类
 */
public interface UserGranter{


	/**
	 * 获取数据
	 * @param loginReq 传入的参数
	 * 		0:账号密码
	 * 	    1:短信验证
	 * 		2:微信授权
	 * @return map值
	 */
	LoginResp login(LoginReq loginReq);

}
