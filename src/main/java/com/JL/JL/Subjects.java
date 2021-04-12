package com.JL.JL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="subjects")
public class Subjects {
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String subcode,subname,facultyid;
	private List <String> substuds = new ArrayList<String>();
	private List <String> materials = new ArrayList<String>();
	private List <String> Assignment = new ArrayList<String>();
	
	public Subjects() {
		
	}
	
	public Subjects(String subcode,String subname,List<String> substuds,List<String> materials,List<String> Assignment) {
		this.Assignment=Assignment;
		this.materials=materials;
		this.subcode=subcode;
		this.subname=subname;
		this.substuds=substuds;
	}
	
	
	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public List<String> getSubstuds() {
		return substuds;
	}

	public void setSubstuds(List<String> substuds) {
		this.substuds = substuds;
	}

	public List<String> getMaterials() {
		return materials;
	}

	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}

	
	public List<String> getAssignment() {
		return Assignment;
	}

	public void setAssignment(List<String> assignment) {
		Assignment = assignment;
	}

	public String getFacultyid() {
		return facultyid;
	}

	public void setFacultyid(String facultyid) {
		this.facultyid = facultyid;
	}
}
