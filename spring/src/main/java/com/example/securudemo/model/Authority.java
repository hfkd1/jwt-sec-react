package com.example.securudemo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,name = "authorityName")
	private String authorityName;

	public Authority(String authorityName) {
		
		this.authorityName=authorityName;
		
	}
	
	public Long getId() {
		return id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@ManyToMany(mappedBy = "groupAuthorities")
    private Collection<GroupAuthority> groupAuthorities;

	public Collection<GroupAuthority> getGroupAuthorities() {
		return groupAuthorities;
	}

	public void setGroupAuthorities(Collection<GroupAuthority> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}
	
}
