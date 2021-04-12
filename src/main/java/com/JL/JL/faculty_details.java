package com.JL.JL;

import java.io.File;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Document(collection="faculty_details")

public class faculty_details {
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name,emailid,phonenumber;
	private List<String> classescreated = new ArrayList<String>();
	public faculty_details() {
		
	}
	
	public faculty_details(String name,String emailid,String phonenumber, List<String> classescreated) {
		this.emailid=emailid;
		this.name=name;
		this.phonenumber=phonenumber;
		this.classescreated=classescreated;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public List<String> getClassescreated() {
		return classescreated;
	}

	public void setClassescreated(List<String> classescreated) {
		this.classescreated = classescreated;
	}

}
