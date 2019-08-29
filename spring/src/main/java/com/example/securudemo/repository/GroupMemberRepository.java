package com.example.securudemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securudemo.model.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
	
	GroupMember findByGroupMemberId(Long id);
	GroupMember findByUserName(String userName);

}
