package com.example.securudemo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

//lombok a geçilecek

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, name = "user_name")
	private String username;

	@Column(nullable = false, name = "password")
	private String password;
	
	
	
	private int active;
//	private String roles;
//	private String permissions;
	
	
	
	
	public User(String username, String password) {
		
		this.username=username;
		this.password=password;
//		this.role=role;
//		this.roles=roles;
//		this.permissions=permissions;	
		this.active = 1;
		
	}
	
	protected User() {
		
	}
	
	
	
	@ManyToMany(mappedBy = "groupMembers")
    private Collection<GroupMember> groupMembers;
	
	public Collection<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Collection<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public Long getId() {
		return id;
	}



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getActive() {
        return active;
    }
	
	public void setActivate(int activate) {
		this.active=activate;
	}
	
	
	

//	public String getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}
//
//
//	public String getPermissions() {
//		return permissions;
//	}
//
//
//	public void setPermissions(String permissions) {
//		this.permissions = permissions;
//	}
//
//	public List<String> getPermissionList(){
//		if(this.permissions.length()>0) {
//			return Arrays.asList(this.permissions.split(","));
//		}
//		return new ArrayList<>();
//	}
//	
//	public List<String> getRoleList(){
//        if(this.roles.length() > 0){
//            return Arrays.asList(this.roles.split(","));
//        }
//        return new ArrayList<>();
//    }
	
	
}
