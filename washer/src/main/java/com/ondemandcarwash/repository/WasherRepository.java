package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ondemandcarwash.model.Washer;
@Repository
public interface WasherRepository extends MongoRepository<Washer, Integer> {
	
	Washer findBywEmail(String wEmail);


} 

