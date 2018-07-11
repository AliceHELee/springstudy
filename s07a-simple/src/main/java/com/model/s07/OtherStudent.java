package com.model.s07;
import javax.annotation.*;

public class OtherStudent{
	private String name;
	private String age;

	public OtherStudent(String name, String age){ 
		super();
		this.name = name;
		this.age = age; 
	}
	
	public String getName() {		return name;	}
	public String getAge() {		return age;	}
	public void setName(String Name) {		this.name = name;	}
	public void setAge(String age) {		this.age = age;	}

	@SuppressWarnings("restriction")
	@PostConstruct
	public void initMethod() {
		System.out.println("initMethod()");
	}
	
	@SuppressWarnings("restriction")
	@PreDestroy
	public void destroyMethod(){
		System.out.println("destroyMethod()");
	}
}
