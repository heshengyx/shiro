package com.my.aop.advice;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.springframework.aop.MethodBeforeAdvice;

import com.caucho.hessian.client.HessianProxyFactory;
import com.my.hessian.LoggerService;

public class BeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("方法开始，方法名[" + method.getName() + "]");
		
		String url = "http://127.0.0.1:8081/aop/hessian/loggerService";  
        HessianProxyFactory factory = new HessianProxyFactory();  
        try {  
        	LoggerService loggerService = (LoggerService) factory.create(  
        			LoggerService.class, url);  

            System.out.println("\r\nGet space names:");  
            loggerService.log(); 
            System.out.println("space names list finished");  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } 
	}

}
