package com.ondemandcarwash;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.repository.WasherRepository;
import com.ondemandcarwash.service.WasherService;

@SpringBootTest
@RunWith(SpringRunner.class)
class WasherApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private WasherService service;



	@MockBean
	private  WasherRepository repository;



	@Test
	public void getCustomersTest() {
	when(repository.findAll()).thenReturn(Stream
	.of(new Washer(37, "Danile","546477","danial@gamil","USA","dany123"),
	new Washer(39, "Danile","546477","danial@gamil","USA","dany123"),
	new Washer(38, "Danile1","5464779","danial12@gamil","USA","dany123")).collect(Collectors.toList()));
	assertEquals(3, service.getWashers().size());
	}

	@Test
	public void saveUserTest() {
	Washer washer = new Washer(37, "Danile","546477","danial@gamil","USA","dany123");
	when(repository.save(washer)).thenReturn(washer);
	assertEquals(washer, service.addWasher(washer));
	}

	@Test
	public void deleteCustomerTest() {
	Washer washer = new Washer(37, "Danile","546477","danial@gamil","USA","dany123");
	service.deleteWasher(washer);
	verify(repository, times(1)).delete(washer);
	}
	}


