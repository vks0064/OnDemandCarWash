package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ondemandcarwash.models.WashPacks;

public interface WashPackRepository extends MongoRepository<WashPacks, Integer> {

}


