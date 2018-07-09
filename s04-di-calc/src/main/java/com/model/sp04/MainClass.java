package com.model.sp04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//MyCalculator myCalculator = new MyCalculator();
		String configLocation = "classpath:applicationCTX.xml";		// src/main/java + src/main/resource					
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class); //객체를 생성한것이 아니라 MyCalculator타입의 객체 주소 저장할 곳 선언
		ctx.close();
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}

}
