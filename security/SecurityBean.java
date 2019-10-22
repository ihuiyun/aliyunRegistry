package com.demo.aop.before.security;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/10/22 18:11
 */
public class SecurityBean {
	public void writeSecurityMessage(){
		System.out.println(Thread.currentThread().getName() + "——————————————————————这是登录成功后的操作————————————————");
	}
}
