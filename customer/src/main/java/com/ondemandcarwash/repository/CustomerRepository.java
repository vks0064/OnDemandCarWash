package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ondemandcarwash.model.Customer;
@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer>{

	 Customer findBycEmail(String cEmail);


}
