package com.example.franchise_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.franchise_api.model.Franchise;

public interface FranchiseRepository extends MongoRepository<Franchise, String> {
}
