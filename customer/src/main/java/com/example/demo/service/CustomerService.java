package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//For CREATING/ADDING  Customer 
		public Customer addCustomer(Customer customer) {
			return customerRepository.save(customer);
			
		}
	     //For getting All Customers
		public List<Customer> getCustomers() {
			List<Customer> customers =customerRepository.findAll();
			System.out.println("Getting Customers from DB" + customers);
			return customers;
		}

		//For deleting By Id
		public void deleteById(int id) {
			customerRepository.deleteById(id);
			
		}
		
		
	}


