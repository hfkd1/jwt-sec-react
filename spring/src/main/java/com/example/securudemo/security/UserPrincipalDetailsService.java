package com.example.securudemo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securudemo.model.GroupMember;
import com.example.securudemo.model.User;
import com.example.securudemo.repository.GroupMemberRepository;
import com.example.securudemo.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{

	
	
	private GroupMemberRepository groupMemberRepository;

    public UserPrincipalDetailsService(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		
		 GroupMember gMember = this.groupMemberRepository.findByUserName(s);
	     UserPrincipal userPrincipal = new UserPrincipal(gMember);

	        return userPrincipal;
	}

}
