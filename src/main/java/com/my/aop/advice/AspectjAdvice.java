package com.my.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectjAdvice {

	@Pointcut("@annotation(com.my.shiro.annotation.ControllerLog)")
	//@Pointcut("execution(* com.my.shiro.controller.*Controller*.*(..))")
	public void pointCut() {
	}
	
	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		//Object[] args = joinPoint.getArgs();
		System.out.println("before aspect executing");
	}
}
