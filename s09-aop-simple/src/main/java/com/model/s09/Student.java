package com.model.s09;
 
public class Student {
	private String name;
	private int age;
	private int gradeNum;
	private int classNum; 
	//public Student(String name, String age, int height, int weight) {
	public Student () { }
	
	public Student(String name, int age, int gradeNum, int classNum) {
		this.name = name;
		this.age = age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
	}
 
	public String getName() {return name;	}
	public void setName(String name) {this.name = name;	}
	public int getAge() {return age;	}
	public void setAge(int age) {this.age = age;	}
	public int getGradeNum() {return gradeNum;	}
	public void setGradeNum(int gradeNum) {this.gradeNum = gradeNum;	}
	public int getClassNum() {return classNum;	}
	public void setClassNum(int classNum) {this.classNum = classNum;	}
	
	public void getStudentInfo() {
		System.out.println("이름:"+ getName());
		System.out.println("나이:"+ getAge());
		System.out.println("학년:"+ getGradeNum());
		System.out.println("반:"+ getClassNum());	
		try {
			System.out.println(10/0);
		} catch(Exception e) {
			System.out.println("zero Div Error");
		}		
	}
}