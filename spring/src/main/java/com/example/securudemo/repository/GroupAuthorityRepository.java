package com.example.securudemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securudemo.model.GroupAuthority;

public interface GroupAuthorityRepository extends JpaRepository<GroupAuthority, Long> {
	GroupAuthority findByGroupAuthorityName(String groupAuthorityName);
	

}
