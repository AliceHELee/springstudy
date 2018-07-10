package com.model.s06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass { 
	public static void main(String[] args) {
	String configLocation = "classpath:applicationCTX.xml";			
	AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);

	Family family = ctx.getBean("family", Family.class);
	ctx.close();
	
	System.out.println(family.getFatherName());
	System.out.println(family.getMotherName());
	System.out.println(family.getBrotherName());
	}
}
