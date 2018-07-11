package com.model.s07;

import org.springframework.context.support.GenericXmlApplicationContext;
 

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:applicationCTX.xml");
		ctx.refresh();
		
		Student student = ctx.getBean("student", Student.class);
		OtherStudent otherStudent = ctx.getBean("otherStudent", OtherStudent.class);
		
		System.out.println("이름1:" + student.getName());
		System.out.println("나이1:" + student.getAge());
		System.out.println("이름2:" + otherStudent.getName());
		System.out.println("나이2:" + otherStudent.getAge());
		
		ctx.close();
		
	}

}
