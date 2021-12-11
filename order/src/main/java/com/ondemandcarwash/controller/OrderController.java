package com.ondemandcarwash.controller;

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
	import com.ondemandcarwash.exception.ApiRequestException;
	import com.ondemandcarwash.model.Order;
	import com.ondemandcarwash.repository.OrderRepository;
	import com.ondemandcarwash.service.OrderService;

	@RestController
	@RequestMapping("/order")
	public class OrderController {

		@Autowired
		private OrderService orderService;

		@Autowired
		private OrderRepository orderRepository;

		// Creating/Adding Order
		@PostMapping("/addorder")
		public String saveOrder(@RequestBody Order order) {
			orderService.addOrder(order);
			return "Order is Placed with Washer and will be Proceesed soon " + order;
		}
		
		//Reading Order by id
		@GetMapping("/order/{id}")
		public Optional<Order> getCustomerById(@PathVariable int id) {
		return orderRepository.findById(id);

		}

		// Reading all washer
		@GetMapping("/allorders")
		public List<Order> getOrder() {
			return orderRepository.findAll();
		}

		// Deleting order by Id
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteOrder(@PathVariable int id) {
			boolean isOrderExist = orderRepository.existsById(id);
			if (isOrderExist) {
				orderService.deleteById(id);
				return new ResponseEntity<Object>("Order deleted with id" + id, HttpStatus.OK);
			} else {
				throw new ApiRequestException("CAN NOT DELETE ORDER ,AS ORDER NOT FOUND WITH THIS ID ::");
			}

		}
	}



