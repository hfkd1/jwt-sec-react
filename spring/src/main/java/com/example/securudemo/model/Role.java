package com.example.securudemo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String roleName;
	
	@Column(nullable = false)
	private String permission;

	public Role() {
		
	}
	
	public Role(String roleName, String permission) {
		
		this.roleName = roleName;
		this.permission = permission;
		
	}
	
	public List<String> getPermissionList(){
		if(this.permission.length()>0) {
			return Arrays.asList(this.permission.split(","));
		}
		return new ArrayList<>();
	}

	public List<String> getRoleList(){
		if(this.roleName.length() > 0){
			return Arrays.asList(this.roleName.split(","));
		}
		return new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
