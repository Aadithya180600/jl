package com.JL.JL;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectsRepository extends MongoRepository<Subjects,String>{
	Subjects findBySubcode(String subcode);

}
