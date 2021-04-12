package com.JL.JL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="AssignmentAns")
public class Assians {
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private String studid,assignmentid;
	private int marks;
	private List<String> studans = new ArrayList<String>();
	
	public Assians() {
		
	}
	
	public Assians(String studid,int marks, List<String> studans,String assignmentid) {
		this.marks=marks;
		this.studans=studans;
		this.studid=studid;
		this.assignmentid=assignmentid;
	}
	
	public String getStudid() {
		return studid;
	}
	public void setStudid(String studid) {
		this.studid = studid;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public List<String> getStudans() {
		return studans;
	}
	public void setStudans(List<String> studans) {
		this.studans = studans;
	}

	public String getAssignmentid() {
		return assignmentid;
	}

	public void setAssignmentid(String assignmentid) {
		this.assignmentid = assignmentid;
	}
}
