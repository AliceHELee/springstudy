package com.model.s06;

import java.util.ArrayList;

public class Student {
	private String name;
	private String age;
	//private int height;
	//private int weight;
	private ArrayList<String> hobbies;
	//public Student(String name, String age, int height, int weight) {
	public Student(String name, String age) {
		this.name = name;
		this.age = age;
	//	this.height = height;
	//	this.weight = weight;
	}


	//public Student(String name, String age, ArrayList<String> hobbies) {
	//public Student(String name, String age) 
	//	this.name = name;
	//	this.age = age;
	//	this.hobbies = hobbies;
	//}

	public String getName() {		return name;	}

	public void setName(String name) {		this.name = name;	}

	public String getAge() {		return age;	}

	public void setAge(String age) {		this.age = age;	}

	//public int getHeight() {		return height;	}

	//public void setHeight(int height) {		this.height = height;	}

	//public int getWeight() {		return weight;	}

	//public void setWeight(int weight) {		this.weight = weight;	}	
}
