package com.example.franchise_api.mapper;

import org.springframework.stereotype.Component;

import com.example.franchise_api.dto.FranchiseCreateDto;
import com.example.franchise_api.model.Franchise;

@Component
public class FranchiseMapper {
	public Franchise toEntity(FranchiseCreateDto dto) {
		Franchise franchise = new Franchise();
			franchise.setName(dto.getName());
		return franchise;
	}

	public FranchiseCreateDto toDto(Franchise entity) {
		return new FranchiseCreateDto(entity.getName());
	}
}
