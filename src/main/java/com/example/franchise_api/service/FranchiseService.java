package com.example.franchise_api.service;

import org.springframework.stereotype.Service;
import com.example.franchise_api.dto.BranchesCreateDto;
import com.example.franchise_api.dto.FranchiseCreateDto;
import com.example.franchise_api.exception.FranchiseNotFoundException;
import com.example.franchise_api.mapper.BranchesMapper;
import com.example.franchise_api.mapper.FranchiseMapper;
import com.example.franchise_api.model.Branch;
import com.example.franchise_api.model.Franchise;
import com.example.franchise_api.repository.FranchiseRepository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class FranchiseService {
	private final FranchiseRepository repository;
	private final FranchiseMapper mapper;
	private final BranchesMapper branchesMapper;
	private final MongoTemplate mongoTemplate;

	public FranchiseService(FranchiseRepository repository, FranchiseMapper mapper, BranchesMapper branchesMapper,
			MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mapper = mapper;
		this.branchesMapper = branchesMapper;
		this.mongoTemplate = mongoTemplate;
	}

	public FranchiseCreateDto save(FranchiseCreateDto dto) {
		Franchise entity = mapper.toEntity(dto);
		Franchise saved = repository.save(entity);
		return mapper.toDto(saved);
	}

	public @Nullable Franchise findFranchiseIdAndIncrementBranch(BranchesCreateDto dto, String id) {
		Branch branch = branchesMapper.toEntity(dto);
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().push("branches", branch);
		Franchise updated = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),
				Franchise.class);
		return Optional.ofNullable(updated).orElseThrow(() -> new FranchiseNotFoundException(id));
	}

}
