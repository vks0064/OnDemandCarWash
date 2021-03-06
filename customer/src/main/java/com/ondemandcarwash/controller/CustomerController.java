package com.ondemandcarwash.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ondemandcarwash.model.Customer;
import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.model.PaymentDetails;
import com.ondemandcarwash.model.Ratings;
import com.ondemandcarwash.model.WashPacks;
import com.ondemandcarwash.repository.CustomerRepository;
import com.ondemandcarwash.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
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
	public Optional<Customer> getCustomerById(@PathVariable int id) 
	{
		return customerRepository.findById(id);
	}
	
	
	//Updating Customer Data by Id
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer)
	{
		 
			customerRepository.save(customer);
			return new ResponseEntity<Object>("user updated successfully with id " +id, HttpStatus.OK);
		
	}
	
	// Deleting Customer Data by Id 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCustomer (@PathVariable int id)
	{
		
			customerService.deleteById(id);
			return new ResponseEntity<Object>("user deleted with id"+id,HttpStatus.OK);
				

	}
	
	
	// For Adding Order
	@PostMapping("/addorder")
	public String addOrder (@RequestBody Order order)
	{
	return restTemplate.postForObject("http://localhost:8083/order/addorder", order , String.class);
	}
	
	
	// for Deleting Order
	@DeleteMapping("/cancelorder/{id}")
	public String deleteorder(@PathVariable("id") int id)
	{
	restTemplate.delete("http://localhost:8083/order/delete/"+id , String.class);
	return "Your Order is successfully Canceled " + id;
	}

	//Reading all washpacks
	@GetMapping("/allpacks")
	public List<WashPacks> getallpack()
	{
	String baseurl="http://localhost:8081/admin/allpacks";
	WashPacks[] allwashpack=restTemplate.getForObject(baseurl, WashPacks[].class);

	return Arrays.asList(allwashpack);
	}

	//For adding ratings
	@PostMapping("/addrating")
	public String addrating(@RequestBody Ratings rating)
	{
	return restTemplate.postForObject("http://localhost:8081/admin/addrating", rating , String.class);
	}
	
	//customer making an payment
	@PostMapping("/payment")
	public String payment(@RequestBody PaymentDetails payment) {


	return restTemplate.postForObject("http://localhost:8084/payment/payments",payment, String.class);



	}

}



