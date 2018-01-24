package com.optum.microservices.provider.model;

import java.util.List;

public class Customer {

	private Integer id;
	private String pesel;
	private String name;
	private ClaimType type;
	private List<Account> accounts;

	public Customer() {
		
	}
	
	public Customer(Integer id, String pesel, String name, ClaimType type) {
		this.id = id;
		this.pesel = pesel;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClaimType getType() {
		return type;
	}

	public void setType(ClaimType type) {
		this.type = type;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
