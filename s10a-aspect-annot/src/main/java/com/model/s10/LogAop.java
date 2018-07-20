package com.model.s10;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
		
		@Pointcut("within(com.model.s10.*)")
		private void pointcutMethod() {
		}
		
		@Around("pointcutMethod()")
		public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		// 전처리
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start.");
		long st = System.currentTimeMillis();			

		// 핵심코드
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
		// 후처리
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + "경과시간 : " + (et-st));
		}
	}
	
	@Before("within(com.model.s10.*)")
	public void beforeAdvice() {
		System.out.println("### beforeAdvice");
	}	
}
