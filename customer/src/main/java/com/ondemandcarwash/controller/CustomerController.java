package com.ondemandcarwash.controller;
	
	import java.util.Arrays;
	import java.util.List;
	import java.util.Optional;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	import com.ondemandcarwash.model.CustomerAuthResponse;
	import com.ondemandcarwash.model.Order;
	import com.ondemandcarwash.model.Ratings;
	import com.ondemandcarwash.model.WashPacks;
	import com.ondemandcarwash.repository.CustomerRepository;
	import com.ondemandcarwash.service.CustomerService;
	
	@RestController
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/customer")
	public class CustomerController {
	
		@Autowired
		private CustomerService customerService;
	
		@Autowired
		private CustomerRepository customerRepository;
	
		@Autowired
		private RestTemplate restTemplate;
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
	
/*/Creating/ADDING Customer
		@PostMapping("/addcustomer")
		public Customer saveCustomer(@RequestBody Customer customer) 
		{
		return customerService.addCustomer(customer);
		}
	*/
//Reading all Customer 
		@GetMapping("/allcustomer")
		public List<Customer> findAllCustomers()
		{
		return customerService.getCustomers();
	    }
	
//Reading Customer by ID
		@GetMapping("/allcustomer/{id}")
		public Optional<Customer> getCustomerById(@PathVariable int id)
		{
		return customerRepository.findById(id);
	    }
	
	
//Updating Customer Data by Id
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer)
		{
		{
    	 customerRepository.save(customer);
			return new ResponseEntity<Object>("user updated successfully with id " +id, HttpStatus.OK);
		}
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
		return restTemplate.postForObject("http://order-service/order/addorder", order , String.class);
		}

// for Deleting Order
		@DeleteMapping("/cancelorder/{id}")
		public String deleteorder(@PathVariable("id") int id)
		{
		restTemplate.delete("http://order-service/order/delete/" +id , String.class);
		return "Your Order is successfully Canceled " + id;
		}
	
	

//Reading all washpacks
	@GetMapping("/allpacks")
	public List<WashPacks> getallpack()
		{
		String baseurl="http://admin-service/admin/allpacks";
			WashPacks[] allwashpack=restTemplate.getForObject(baseurl, WashPacks[].class);
			return Arrays.asList(allwashpack);
		}
//For adding ratings
	@PostMapping("/addrating")
	public String addrating(@RequestBody Ratings rating)
		{
			return restTemplate.postForObject("http://admin-service/admin/addrating", rating , String.class);
		}
/*/customer making an payment
	 @PostMapping("/payment")
	 public String payment(@RequestBody Payments payment) {
		 return restTemplate.postForObject("http://localhost:8084/payment/pay",payment, String.class);
	  }*/
	 
 //add custometer
	 	@PostMapping("/addcustomer")
		private ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
			
			String email = customer.getcEmail();
			String password = customer.getcPassword();
			Customer customer1 = new Customer();
			customer1.setcEmail(email);
			customer1.setcPassword(password);
			try {
				customerRepository.save(customer);
				
			} catch (Exception e) {
				return ResponseEntity.ok(new CustomerAuthResponse("Error creating customer "+ email));
			}
			return ResponseEntity.ok(new CustomerAuthResponse("Successfully created customer "+ email));
			
			
		}
	//authenticating customer
			@PostMapping("/auth")
			private ResponseEntity<?> authCustomer(@RequestBody Customer customer){
				String email = customer.getcEmail();
				String password = customer.getcPassword();
				try {
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
					
				} catch (Exception e) {
					
					return ResponseEntity.ok(new CustomerAuthResponse("Error during  customer Authentication"+ email));
				}
				return ResponseEntity.ok(new CustomerAuthResponse("Successfully Authenticated customer "+ email));
				
				}
	 }