package com.example.demo.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.google.common.base.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Creating/ADDING Customer
	@PostMapping("/addcustomer")
	public Customer saveCustomer(@RequestBody Customer customer) 
	{
		 return customerService.addCustomer(customer);
	
	}
	
	//Reading all Customer 
	@GetMapping("/allcustomers")
	public List<Customer> findAllCustomers()
	{
		return customerService.getCustomers();
		
	}
	
	//Reading Customer by ID
	@GetMapping("/allcustomers/{id}")
	public Optional<Customer> getCustomerById(@PathVariable int id) throws ApiRequestException
	{
		return Optional.of(customerRepository.findById(id)
				.orElseThrow(()-> new ApiRequestException("CUSTOMER NOT FOUND WITH THIS ID ::")));
	}
	
	
	//Updating Customer Data by Id
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer)
	{
		boolean isCustomerExist=customerRepository.existsById(id);
		if(isCustomerExist) {
			customerRepository.save(customer);
			return new ResponseEntity<Object>("user updated successfully with id " +id, org.springframework.http.HttpStatus.OK);
		}
		else 
		{
			throw new ApiRequestException("CAN NOT UPDATE AS USER NOT FOUND WITH THIS ID ::");
		}
		
	}
	
	// Deleting Customer Data by Id 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCustomer (@PathVariable int id)
	{
		boolean isCustomerExist=customerRepository.existsById(id);
		if(isCustomerExist) {
			customerService.deleteById(id);
			return new ResponseEntity<Object>("user deleted with id"+id,org.springframework.http.HttpStatus.OK);
		}
		else
		{
			throw new ApiRequestException("CAN NOT DELETE AS USER NOT FOUND WITH THIS ID ::");
		}

	}
	

}


