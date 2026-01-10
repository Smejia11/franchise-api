package com.example.franchise_api.mapper;

import org.springframework.stereotype.Component;

import com.example.franchise_api.dto.ProductCreateDto;
import com.example.franchise_api.model.Product;

@Component
public class ProductMapper {
	public Product toEntity(ProductCreateDto dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setStock(dto.getStock());
		return product;
	}
}
