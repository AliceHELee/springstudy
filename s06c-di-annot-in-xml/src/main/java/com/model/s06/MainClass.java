package com.model.s06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Student student1 = ctx.getBean("student1", Student.class);		
		System.out.println("이름: " + student1.getName());
		
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println("이름: " + student2.getName());
		ctx.close();
	}

}
