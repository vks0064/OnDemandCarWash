package com.ondemandcarwash.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ondemandcarwash.models.Admin;
import com.ondemandcarwash.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService
{



@Autowired
private AdminRepository adminRepository;

//Creating and adding admin
public Admin addAdmin(Admin admin)
{
return adminRepository.save(admin);

}

//Reading All admin
public List<Admin> getAdmins()
{
List<Admin> admins =adminRepository.findAll();
System.out.println("Getting Admins from DB" + admins);
return admins;
}

//For deleting By Id
public void deleteById(int id)
{
adminRepository.deleteById(id);

}

//detete all admin

public void deleteAdmin(Admin admin) {
	adminRepository.delete(admin);
}
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Admin foundedAdmin = adminRepository.findByaEmail(username);
	
	if  (foundedAdmin ==null) return null;
	String aEmail = foundedAdmin.getaEmail();
	String aPassword = foundedAdmin.getaPassword();
	return new User(aEmail, aPassword, new ArrayList<>());
}


}