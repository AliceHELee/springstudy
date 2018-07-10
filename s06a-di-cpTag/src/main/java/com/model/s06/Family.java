package com.model.s06;

public class Family {
	String fatherName;
	String motherName;
	String brotherName;
	
	public Family(String fatherName, String motherName) {
		this.fatherName = fatherName;
		this.motherName = motherName;
	}
 
	public String getFatherName() {		return fatherName;	}
	public String getMotherName() {		return motherName;	}
	public String getBrotherName() {		return brotherName;	}
	
	public void setFatherName(String fatherName) {		this.fatherName = fatherName;	}	
	public void setMotherName(String motherName) {		this.motherName = motherName;	}	
	public void setBrotherName(String brotherName) {		this.brotherName = brotherName;	}
	
}
