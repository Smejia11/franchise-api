package com.example.franchise_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.franchise_api.dto.BranchesCreateDto;
import com.example.franchise_api.dto.FranchiseCreateDto;
import com.example.franchise_api.dto.ProductCreateDto;
import com.example.franchise_api.service.FranchiseService;

@RestController
@RequestMapping("v1/franchises")
public class FranchiseController {
	private final FranchiseService franchiseService;

	public FranchiseController(FranchiseService franchiseService) {
		this.franchiseService = franchiseService;
	}

	@PostMapping
	public ResponseEntity<Void> franchises(@RequestBody FranchiseCreateDto franchiseCreateDto) throws Exception {
		this.franchiseService.save(franchiseCreateDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/{franchiseName}/branches")
	public ResponseEntity<Void> addBranchToFranchises(@PathVariable String franchiseName,
			@RequestBody BranchesCreateDto branchesCreateDto) throws Exception {
		this.franchiseService.addBranchToFranchises(branchesCreateDto, franchiseName);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/{franchiseName}/branches/{branchName}/products")
	public ResponseEntity<Void> addProductToBranch(@PathVariable String franchiseName, @PathVariable String branchName,
			@RequestBody ProductCreateDto productCreateDto) throws Exception {
		this.franchiseService.addProductToBranch(productCreateDto, franchiseName, branchName);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{franchiseName}/branches/{branchName}/products/{productName}")
	public ResponseEntity<Void> deleteProductToBranch(@PathVariable String franchiseName,
			@PathVariable String branchName, @PathVariable String productName) throws Exception {
		this.franchiseService.deleteProductToBranch(productName, franchiseName, branchName);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{franchiseName}/branches/{branchName}/products/{productName}/stock/{stock}")
	public ResponseEntity<Void> updateProductStock(@PathVariable String franchiseName, @PathVariable String branchName,
			@PathVariable String productName, @PathVariable Integer stock) throws Exception {
		this.franchiseService.updateProductStock(productName, franchiseName, branchName, stock);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
