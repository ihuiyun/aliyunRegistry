package com.demo.aop.before.security;

/**
 * 描述：用于验证用户，保存凭据
 *
 * @author lida
 * @time 2019/10/22 20:12
 */
public class SecurityManager {
	private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

	/**
	 * 登录存储用户信息
	 * @param name 用户名
	 * @param password 密码
	 */
	public void login(String name, String password){
		threadLocal.set(new UserInfo(name, password));
	}

	/**
	 * 退出的时候设置该线程对应的用户信息为null
	 */
	public void logout(){
		threadLocal.set(null);
	}

	public UserInfo getUserInfo(){
		return threadLocal.get();
	}
}
