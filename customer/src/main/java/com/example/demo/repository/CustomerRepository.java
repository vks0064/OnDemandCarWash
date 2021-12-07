package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer,Integer> {

	List<Customer> findAll();

	Customer save(Customer customer);

	void deleteById(int id);

	
	

}
