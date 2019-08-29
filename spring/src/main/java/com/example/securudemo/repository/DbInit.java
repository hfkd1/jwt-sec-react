package com.example.securudemo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import com.example.securudemo.model.Authority;
import com.example.securudemo.model.GroupAuthority;
import com.example.securudemo.model.GroupMember;
import com.example.securudemo.model.Role;
import com.example.securudemo.model.User;

@Service
public class DbInit implements CommandLineRunner{

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private AuthorityRepository authorityRepository;
	
	private GroupAuthorityRepository groupAuthorityRepository;
	
	private GroupMemberRepository groupMemberRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthorityRepository authorityRepository, GroupAuthorityRepository groupAuthorityRepository, GroupMemberRepository groupMemberRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository=roleRepository;
		this.authorityRepository=authorityRepository;
		this.groupAuthorityRepository=groupAuthorityRepository;
		this.groupMemberRepository=groupMemberRepository;
	}
	
	@Override
	public void run(String... args) {
		userRepository.deleteAll();
		//Create Role and User with Username and Password
		
		Authority authority1 = new Authority("ACCESS_TEST1");
		Authority authority2 = new Authority("ACCESS_TEST2");
		Collection<Authority> authorityCollection1 = new ArrayList<Authority>();
		Collection<Authority> authorityCollection2 = new ArrayList<Authority>();
		authorityCollection1.add(authority1);
		authorityCollection2.addAll(Arrays.asList(authority1,authority2));		
		
		GroupAuthority groupAuthority1 = new GroupAuthority("GROUP_AUTHORITY1", authorityCollection1 );
		GroupAuthority groupAuthority2 = new GroupAuthority("GROUP_AUTHORITY2", authorityCollection2 );
		
		Role role1 = new Role("role1", groupAuthority1);
		Role role2 = new Role("role2", groupAuthority2);
		
		User user1 = new User("admin",passwordEncoder.encode("admin"));
		User user2 = new User("user",passwordEncoder.encode("user"));
		
		Collection<User> userCollection1 = new ArrayList<User>();
		Collection<User> userCollection2 = new ArrayList<User>();
		userCollection1.add(user1);
		userCollection2.addAll(Arrays.asList(user1,user2));
		
		GroupMember groupMember1 = new GroupMember(role1, userCollection1);
		GroupMember groupMember2 = new GroupMember(role2, userCollection2);
		
		userRepository.saveAll(Arrays.asList(user1,user2));
		roleRepository.saveAll(Arrays.asList(role1,role2));
		authorityRepository.saveAll(Arrays.asList(authority1,authority2));
		groupAuthorityRepository.saveAll(Arrays.asList(groupAuthority1,groupAuthority2));
		groupMemberRepository.saveAll(Arrays.asList(groupMember1,groupMember2));
		
		
//		Role roleAdmin = new Role("ADMIN");
//		User salih = new User("salih", passwordEncoder.encode("salih"));
//		salih.setRole(roleAdmin);
//		
		
		
		
//		User irsat = new User("irsat", passwordEncoder.encode("irsat"));
//		User admin = new User("admin", passwordEncoder.encode("admin")/*,"ADMIN","ACCESS_TEST1,ACCESS_TEST2"*/);
//		User manager = new User("manager", passwordEncoder.encode("manager")/*,"MANAGER","ACCESS_TEST1"*/);
		
//		List<Role> roles = Arrays.asList(roleAdmin);
//		List<User> users =Arrays.asList(salih,irsat,admin,manager);
//		
		//saving to DB reis
//		this.roleRepository.saveAll(roles);
//		this.userRepository.saveAll(users);
	}
}
