package com.model.s08;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		AdminConnection connection = ctx.getBean("adminConfig", AdminConnection.class);
		
		System.out.println("adminID:" + connection.getAdminId());
		System.out.println("adminPW:" + connection.getAdminPw());		
		System.out.println("sub_adminID:" + connection.getSubAdminId());
		System.out.println("sub_adminPW:" + connection.getSubAdminPw());
		
		ctx.close();
	}

}
