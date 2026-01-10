package com.example.franchise_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.franchise_api.dto.BranchesCreateDto;
import com.example.franchise_api.dto.FranchiseCreateDto;
import com.example.franchise_api.dto.ProductCreateDto;
import com.example.franchise_api.dto.TopProductResponse;
import com.example.franchise_api.dto.UpdateNameBranch;
import com.example.franchise_api.dto.UpdateNameFranchise;
import com.example.franchise_api.dto.UpdateNameProduct;
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

	@PatchMapping("/{franchiseName}/update/name")
	public ResponseEntity<Void> updateName(@PathVariable String franchiseName,
			@RequestBody UpdateNameFranchise franchiseUpdateName) throws Exception {
		this.franchiseService.updateName(franchiseName, franchiseUpdateName);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{franchiseName}/branches/{branchName}/update/name")
	public ResponseEntity<Void> updateNameBranch(@PathVariable String franchiseName, @PathVariable String branchName,
			@RequestBody UpdateNameBranch updateNameBranch) throws Exception {
		this.franchiseService.updateNameBranch(franchiseName, branchName, updateNameBranch);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{franchiseName}/branches/{branchName}/products/{productName}/update/name")
	public ResponseEntity<Void> updateNameProduct(@PathVariable String franchiseName, @PathVariable String branchName,
			@PathVariable String productName, @RequestBody UpdateNameProduct updateNameBranch) throws Exception {
		this.franchiseService.updateNameProduct(franchiseName, branchName, productName, updateNameBranch);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/{franchiseName}/branches")
	public ResponseEntity<Void> addBranchToFranchises(@PathVariable String franchiseName,
			@RequestBody BranchesCreateDto branchesCreateDto) throws Exception {
		this.franchiseService.addBranchToFranchise(branchesCreateDto, franchiseName);
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

	@PatchMapping("/{franchiseName}/branches/{branchName}/products/{productName}/stock/{stock}")
	public ResponseEntity<Void> updateProductStock(@PathVariable String franchiseName, @PathVariable String branchName,
			@PathVariable String productName, @PathVariable Integer stock) throws Exception {
		this.franchiseService.updateProductStock(productName, franchiseName, branchName, stock);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/{franchiseName}/branches/products/top")
	public ResponseEntity<List<TopProductResponse>> getTopStockProducts(@PathVariable String franchiseName)
			throws Exception {
		List<TopProductResponse> topProductResponse = this.franchiseService.getTopStockProducts(franchiseName);
		return ResponseEntity.status(HttpStatus.OK).body(topProductResponse);
	}
}
