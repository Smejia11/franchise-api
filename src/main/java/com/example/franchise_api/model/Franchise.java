package com.example.franchise_api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "franchises")
public class Franchise {

    @Id
    private String id;
    private String name;
    private List<Branch> branches = new ArrayList<>();
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the branches
	 */
	public List<Branch> getBranches() {
		return branches;
	}
	/**
	 * @param branches the branches to set
	 */
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
}
