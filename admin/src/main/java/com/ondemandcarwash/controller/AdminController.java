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
     import org.springframework.web.client.RestTemplate;




    import com.ondemandcarwash.models.Admin;
    import com.ondemandcarwash.models.Order;
    import com.ondemandcarwash.models.Ratings;
    import com.ondemandcarwash.models.WashPacks;
    import com.ondemandcarwash.repository.AdminRepository;
    import com.ondemandcarwash.repository.RatingRepository;
    import com.ondemandcarwash.repository.WashPackRepository;
    import com.ondemandcarwash.service.AdminService;



     @RestController
     @RequestMapping("/admin")
     public class AdminController {

      @Autowired
     private AdminRepository adminRepository;
      
      @Autowired
      private WashPackRepository washpackrepository;
      
      @Autowired
      private RatingRepository ratingrepository;
      

      @Autowired
     private RestTemplate restTemplate;

      @Autowired
      private AdminService adminService;
     //Creating/ADDING Admin
      @PostMapping("/addadmin")
      public Admin saveCustomer(@RequestBody Admin admin)
    {
      return adminService.addAdmin(admin);



    }





     //Reading all Admin
     @GetMapping("/alladmins")
    public List<Admin> findAllCustomers()
    {
   return adminService.getAdmins();



   }



    //Reading Customer by ID
    @GetMapping("/alladmins/{id}")
    public Optional<Admin> getAdminById(@PathVariable int id)
   {
   return adminRepository.findById(id);
   }




      //Updating Admin Data by Id
       @PutMapping("/update/{id}")
     public ResponseEntity<Object> updateAdmin(@PathVariable int id, @RequestBody Admin admin)
    {



      adminRepository.save(admin);
     return new ResponseEntity<Object>("Admininfo updated successfully with id " +id, HttpStatus.OK);




     }



     // Deleting Admin Data by Id
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<Object> deleteAdmin (@PathVariable int id)
     {



    adminService.deleteById(id);
   return new ResponseEntity<Object>("Admininfo deleted with id"+id,HttpStatus.OK);



    }


    //Reading orders By
    @GetMapping("/getallorders/{id}")
    public Order getOrderById (@PathVariable("id") int id)
   {
    return restTemplate.getForObject("http://localhost:8083/order/order/" +id , Order.class);

    }



       //Reading All orders
       @GetMapping("/getallorders")
       public String getallOrder()
    {
    return restTemplate.getForObject("http://localhost:8083/order/allorders", String.class);

    }
     //adding washpack by admin
       @PostMapping("/addpack")
       public String savepack(@RequestBody WashPacks pack)
       {
       washpackrepository.save(pack);
       return " Pack saved successfully with id :"+pack.getId();
       }
       //get all washpack by admin
       @GetMapping("/allpacks")
       public List<WashPacks> getpack()
       {
       return washpackrepository.findAll();
       }

       @DeleteMapping("/deletepack/{id}")
       public String deletepack(@PathVariable int id)
       {
       washpackrepository.deleteById(id);
       return "pack deleted with id "+id;
       }
       
     //get mapping rating by admin
       @GetMapping("/allratings")
       public List<Ratings> getuser()
       {
       return ratingrepository.findAll();
       }
       //add rating by admin
       @PostMapping("/addrating")
       public String saverating(@RequestBody Ratings rating)
       {
       ratingrepository.save(rating);
       return " Thanks for Your Valuable feedback";
       }
     }
     
	

	


