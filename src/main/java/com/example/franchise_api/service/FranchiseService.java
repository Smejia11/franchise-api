package com.example.franchise_api.service;

import org.springframework.stereotype.Service;
import com.example.franchise_api.dto.BranchesCreateDto;
import com.example.franchise_api.dto.FranchiseCreateDto;
import com.example.franchise_api.dto.ProductCreateDto;
import com.example.franchise_api.exception.BranchNotFoundException;
import com.example.franchise_api.exception.FranchiseNotFoundException;
import com.example.franchise_api.mapper.BranchesMapper;
import com.example.franchise_api.mapper.FranchiseMapper;
import com.example.franchise_api.mapper.ProductMapper;
import com.example.franchise_api.model.Branch;
import com.example.franchise_api.model.Franchise;
import com.example.franchise_api.model.Product;
import com.example.franchise_api.repository.FranchiseRepository;
import com.mongodb.client.result.UpdateResult;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Optional;

import org.bson.BsonValue;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class FranchiseService {
	private final FranchiseRepository repository;
	private final FranchiseMapper mapper;
	private final BranchesMapper branchesMapper;
	private final MongoTemplate mongoTemplate;
	private final ProductMapper productMapper;

	public FranchiseService(FranchiseRepository repository, FranchiseMapper mapper, BranchesMapper branchesMapper,
			MongoTemplate mongoTemplate, ProductMapper productMapper) {
		this.repository = repository;
		this.mapper = mapper;
		this.branchesMapper = branchesMapper;
		this.mongoTemplate = mongoTemplate;
		this.productMapper = productMapper;
	}

	public FranchiseCreateDto save(FranchiseCreateDto dto) {
		Franchise entity = mapper.toEntity(dto);
		Franchise saved = repository.save(entity);
		return mapper.toDto(saved);
	}

	public @Nullable Franchise addBranchToFranchises(BranchesCreateDto dto, String name) {
		Branch branch = branchesMapper.toEntity(dto);
		Query query = new Query(Criteria.where("name").is(name));
		Update update = new Update().push("branches", branch);
		Franchise updated = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),
				Franchise.class);
		return Optional.ofNullable(updated).orElseThrow(() -> new FranchiseNotFoundException(name));
	}

	public void addProductToBranch(ProductCreateDto dto, String franchiseName, String branchName) {
		boolean franchiseExists = mongoTemplate.exists(Query.query(Criteria.where("name").is(franchiseName)),
				Franchise.class);
		if (!franchiseExists) {
			throw new FranchiseNotFoundException(franchiseName);
		}
		Product product = productMapper.toEntity(dto);
		Query query = new Query(Criteria.where("name").is(franchiseName).and("branches.name").is(branchName));
		Update update = new Update().push("branches.$[b].products", product)
				.filterArray(Criteria.where("b.name").is(branchName));

		UpdateResult result = mongoTemplate.updateFirst(query, update, Franchise.class);

		if (result.getModifiedCount() == 0 || result.getMatchedCount() == 0) {
			throw new BranchNotFoundException(branchName);
		}

	}

	public void deleteProductToBranch(String productName, String franchiseName, String branchName) {
		boolean franchiseExists = mongoTemplate.exists(Query.query(Criteria.where("name").is(franchiseName)),
				Franchise.class);
		if (!franchiseExists) {
			throw new FranchiseNotFoundException(franchiseName);
		}
		Query query = new Query(Criteria.where("name").is(franchiseName).and("branches.name").is(branchName));

		Update update = new Update().pull("branches.$.products", Query.query(Criteria.where("name").is(productName)));

		UpdateResult result = mongoTemplate.updateFirst(query, update, Franchise.class);
		if (result.getModifiedCount() == 0 || result.getMatchedCount() == 0) {
			throw new BranchNotFoundException(branchName);
		}

	}

	public void updateProductStock(String productName, String franchiseName, String branchName, Integer stock) {
		// TODO Auto-generated method stub

	}

}
