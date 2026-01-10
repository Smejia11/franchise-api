package com.example.franchise_api.mapper;

import org.springframework.stereotype.Component;

import com.example.franchise_api.dto.BranchesCreateDto;
import com.example.franchise_api.model.Branch;

@Component
public class BranchesMapper {
	public Branch toEntity(BranchesCreateDto dto) {
		Branch branch = new Branch();
		branch.setName(dto.getName());
		return branch;
	}

}
