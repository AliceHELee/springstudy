package com.model.s06;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 

@Configuration
public class ApplicationConfig {
	
	@Bean	
	public Student student1() {
		
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("수영");
		hobbies.add("요리");
		
		Student student = new Student("오드리햇반", "25");
		//student.setHeight(165);
		//student.setWeight(45);
		
		return student;
	}
	
	@Bean	
	public Student student2() {
		
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("말아톤");
		hobbies.add("에어로빅");
		
		Student student = new Student("고리고리", "35");
		//student.setHeight(175);
		//student.setWeight(55);
		
		return student;
	}
}
