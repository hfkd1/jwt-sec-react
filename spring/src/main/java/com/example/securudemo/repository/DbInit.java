package com.example.securudemo.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securudemo.model.Role;
import com.example.securudemo.model.User;

@Service
public class DbInit implements CommandLineRunner{

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository=roleRepository;
	}
	
	@Override
	public void run(String... args) {
		userRepository.deleteAll();
		//Create Role and User with Username and Password
		Role roleAdmin = new Role("ADMIN","ACCESS_TEST1");
		User salih = new User("salih", passwordEncoder.encode("salih"));
		salih.setRole(roleAdmin);
		
		
		
		
		User irsat = new User("irsat", passwordEncoder.encode("irsat"));
		User admin = new User("admin", passwordEncoder.encode("admin")/*,"ADMIN","ACCESS_TEST1,ACCESS_TEST2"*/);
		User manager = new User("manager", passwordEncoder.encode("manager")/*,"MANAGER","ACCESS_TEST1"*/);
		
		List<Role> roles = Arrays.asList(roleAdmin);
		List<User> users =Arrays.asList(salih,irsat,admin,manager);
		
		//saving to DB reis
		this.roleRepository.saveAll(roles);
		this.userRepository.saveAll(users);
	}
}
