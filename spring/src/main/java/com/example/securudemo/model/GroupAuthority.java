package com.example.securudemo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class GroupAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,name = "groupAuthorityName")
	private String groupAuthorityName;
	
	
	
	@ManyToMany
    @JoinTable(
        name = "groupAuthorities", 
        joinColumns = @JoinColumn(
          name = "groupAuthorityId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "authorityId", referencedColumnName = "id"))
	private Collection<Authority> groupAuthorities;
	
	@OneToOne(mappedBy = "roleGroup")
    private Role roleGroup;

	public GroupAuthority(String groupAuthorityName, Collection<Authority> authority) {
		super();
		this.groupAuthorityName = groupAuthorityName;
		this.groupAuthorities = groupAuthorities;
	}

	public String getGroupAuthorityName() {
		return groupAuthorityName;
	}

	public void setGroupAuthorityName(String groupAuthorityName) {
		this.groupAuthorityName = groupAuthorityName;
	}

	public Collection<Authority> getAuthority() {
		return groupAuthorities;
	}

	public void setAuthority(Collection<Authority> authority) {
		this.groupAuthorities = authority;
	}

	public Role getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(Role roleGroup) {
		this.roleGroup = roleGroup;
	}

	public Long getId() {
		return id;
	}
}
