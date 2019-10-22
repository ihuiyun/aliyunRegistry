package com.demo.aop.before.security;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.aop.framework.ProxyFactory;

import java.util.concurrent.*;

/**
 * 描述：多线程环境下的测试
 *
 * @author lida
 * @time 2019/10/22 20:46
 */
public class SecurityDemo {

	public static void main(String[] args) throws InterruptedException {
		SecurityManager mgr = new SecurityManager();

		//得到代理后的对象
		SecurityBean bean = getSecureBean();

		//自定义一个线程池，使用可命名的线程工厂，默认拒绝策略为AbortPolicy, 但是出现拒绝后线程池无法关闭？核心线程随机保留
		//测试显示在等待任务
		//第二次使用DiscardOldestPolicy,线程池可以正常关闭
		ExecutorService executors = new ThreadPoolExecutor(4,6,
				1,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(20),
				new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
				new ThreadPoolExecutor.DiscardOldestPolicy());

		int max = 30;
		for (int i = 0; i < max; i++) {
			executors.submit(() -> {
				try {
					mgr.login(Thread.currentThread().getName(), "pwd");
					bean.writeSecurityMessage();
				} catch (SecurityException e){
					System.out.println("Exception caught: " + e.getMessage());
				}finally {
					mgr.logout();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Interrupt Exception");;
					}
				}
			});
		}

		executors.shutdown();
	}

	private static SecurityBean getSecureBean() {
		SecurityBean target = new SecurityBean();

		SecurityAdvice advice = new SecurityAdvice();

		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(advice);
		pf.setTarget(target);

		SecurityBean proxy = (SecurityBean)pf.getProxy();
		return proxy;
	}
}
