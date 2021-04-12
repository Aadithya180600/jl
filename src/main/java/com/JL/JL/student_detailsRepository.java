package com.JL.JL;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface student_detailsRepository extends MongoRepository<student_details, String>{
	student_details findByRollnumber(String rollnumber);
	student_details findByPhoneno(String phoneno);
	student_details findByEmailid(String emailid);
}
