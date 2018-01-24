package com.optum.microservices.provider.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optum.microservices.provider.intercomm.AccountClient;
import com.optum.microservices.provider.model.Account;
import com.optum.microservices.provider.model.Customer;
import com.optum.microservices.provider.model.ClaimType;

@RestController
public class Api {
	
	@Autowired
	private AccountClient accountClient;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	private List<Customer> customers;
	
	public Api() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "12345", "Rohit", ClaimType.INDIVIDUAL));
		customers.add(new Customer(2, "12346", "Vishal", ClaimType.COMPANY));
		customers.add(new Customer(3, "12347", "Manvinder", ClaimType.COMPANY));
		customers.add(new Customer(4, "12348", "Kallol", ClaimType.INDIVIDUAL));
	}
	
	@RequestMapping("/provider/pesel/{pesel}")
	public Customer findByPesel(@PathVariable("pesel") String pesel) {
		logger.info(String.format("Customer.findByPesel(%s)", pesel));
		return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();	
	}
	
	@RequestMapping("/provider")
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		return customers;
	}
	
	@RequestMapping("/provider/{id}")
	public Customer findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		List<Account> accounts =  accountClient.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}
	
}
