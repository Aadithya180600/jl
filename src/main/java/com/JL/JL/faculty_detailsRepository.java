package com.JL.JL;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface faculty_detailsRepository extends MongoRepository<faculty_details, String> {
	faculty_details findByEmailid(String emailid);

}
