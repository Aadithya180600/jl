package com.JL.JL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Assignments")
public class Assignments {
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private String assiname,assique;
	private int assimarks;
	private List<String> assians = new ArrayList<>();
	
	public Assignments() {
		
	}
	
	public Assignments(String assiname,String assique,int assimarks,List<String> assians) {
		this.assians=assians;
		this.assimarks=assimarks;
		this.assiname=assiname;
		this.assique=assique;
	}
	
	public String getAssiname() {
		return assiname;
	}
	public void setAssiname(String assiname) {
		this.assiname = assiname;
	}
	public String getAssique() {
		return assique;
	}
	public void setAssique(String assique) {
		this.assique = assique;
	}
	public int getAssimarks() {
		return assimarks;
	}
	public void setAssimarks(int assimarks) {
		this.assimarks = assimarks;
	}

	public List<String> getAssians() {
		return assians;
	}

	public void setAssians(List<String> assians) {
		this.assians = assians;
	}
}
