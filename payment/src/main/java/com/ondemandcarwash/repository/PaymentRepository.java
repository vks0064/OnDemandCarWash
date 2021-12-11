package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ondemandcarwash.model.PaymentDetails;

public interface PaymentRepository extends MongoRepository<PaymentDetails, Integer> {


} 


