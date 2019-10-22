package com.demo.aop.before.security;

/**
 * 描述：User Bean
 *		保存关于用户的数据
 * @author lida
 * @time 2019/10/22 20:08
 */
public class UserInfo {
	private String name;
	private String password;

	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
