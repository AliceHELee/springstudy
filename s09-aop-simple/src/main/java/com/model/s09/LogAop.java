package com.model.s09;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	public Object loggerAop(ProceedingJoinPoint joinpoin) throws Throwable {
		//전처리
		String signatureStr = joinpoin.getSignature().toShortString();
		System.out.println(signatureStr + "is start.");
		long st = System.currentTimeMillis();
		
		
		//핵심코드
		try {
			Object obj = joinpoin.proceed();
			return obj;
		} finally {			
			//후처리
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + "경과시간:" + (et-st));
		}
	}	
	
	public void beforeAdvice(JoinPoint joininPoint) {
		System.out.println("### BeforeAdvice()");
	}
	public void afterAdvice(JoinPoint joininPoint) {
		System.out.println("### afterAdvice()");
	}
	public void afterThrowingAdvice(JoinPoint joininPoint) {
		System.out.println("### afterThrowingAdvice()");
	}
	public void afterReturningAdvice(JoinPoint joininPoint) {
		System.out.println("### afterReturningAdvice()");
	}	
	
}
