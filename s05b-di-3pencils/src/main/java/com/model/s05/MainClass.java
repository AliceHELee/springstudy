package com.model.s05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
 

public class MainClass { 
	//String configLocation = "classpath:applicationCTX.xml";
	AbstractApplicationContext ctx = 
			new GenericXmlApplicationContext("classpath:applicationCTX.xml");
	
	Pencil pencil = ctx.getBean("pencil", Pencil.class);  
	
	pencil.use();
	ctx.close();
	}
}
