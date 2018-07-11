package com.model.s07;

import java.util.ArrayList;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean {
	private String name;
	private String age;

	public Student(String name, String age){ 
		super();
		this.name = name;
		this.age = age; 
	}
 
	public String getName() {		return name;	}
	public String getAge() {		return age;	}
	public void setName(String Name) {		this.name = name;	}
	public void setAge(String age) {		this.age = age;	}
	
	@Override
	public void destroy() throws Exception{
		System.out.println("destroy()");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception{
		System.out.println("afterPropertiesSet()");
	}
	
}
