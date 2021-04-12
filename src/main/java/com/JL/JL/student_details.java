package com.JL.JL;


import java.io.File;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Document(collection="student_details")
public class student_details {
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name,emailid,phoneno,rollnumber;
	private List<String> classesenrolled = new ArrayList<String>();
	

	public student_details() {
		
	}
	
	public student_details(String name,String emailid,String phoneno,String rollnumber,List<String> classesenrolled) {
		this.name=name;
		this.emailid=emailid;
		this.phoneno=phoneno;
		this.rollnumber=rollnumber;
		this.classesenrolled=classesenrolled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
	}
	
	public List<String> getClassesenrolled() {
		return classesenrolled;
	}

	public void setClassesenrolled(List<String> classesenrolled) {
		this.classesenrolled = classesenrolled;
	}
}
