package com.JL.JL;

import java.io.File;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Document(collection="subjects")
public class Newclasses {
	@Id
    private String id;
	
	private List<Subjects> subjects = new ArrayList<Subjects>();
	
	public Newclasses() {
		
	}
	
	public Newclasses(List<Subjects> subjects) {
		this.subjects=subjects;
	}

	public List<Subjects> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
