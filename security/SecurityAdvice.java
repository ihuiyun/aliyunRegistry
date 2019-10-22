package com.demo.aop.before.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/10/22 20:19
 */
public class SecurityAdvice implements MethodBeforeAdvice {
	private Log log = LogFactory.getLog(SecurityAdvice.class);
	private SecurityManager securityManager;
	private final String NAME = "demo-pool-4";

	public SecurityAdvice(){
		this.securityManager = new SecurityManager();
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		UserInfo userInfo = securityManager.getUserInfo();

		if (userInfo == null) {
			log.info("No user authenticated");
			throw new SecurityException("You must login before invoke the method: " + method.getName());
		} else if (NAME.equals(userInfo.getName())){
			log.info("Logged is demo-pool-2 ----OKAY");
		} else {
			log.info("Logged is " + userInfo.getName() + " , but he/she has not authentication");
			throw new SecurityException("User " + userInfo.getName() + " is not allowed to access to method " +
					method.getName());
		}
	}
}
