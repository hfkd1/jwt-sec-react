package com.example.securudemo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Role {

	public Role(String roleName, GroupAuthority groupAuthority) {
		super();
		this.roleName = roleName;
		this.roleGroup = groupAuthority;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String roleName;

	
	
/*	public List<String> getPermissionList(){
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
	
	
*/
	@OneToOne
    @JoinTable(
        name = "roleGroup", 
        joinColumns = @JoinColumn(
          name = "roleId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "groupAuthorityId", referencedColumnName = "id"))
	private GroupAuthority roleGroup;
	
	
	public GroupAuthority getGroupAuthority() {
		return roleGroup;
	}


	public void setGroupAuthority(GroupAuthority groupAuthority) {
		this.roleGroup = groupAuthority;
	}


	public GroupMember getGroupMembersRoles() {
		return groupMembersRoles;
	}


	public void setGroupMembersRoles(GroupMember groupMembersRoles) {
		this.groupMembersRoles = groupMembersRoles;
	}

	@OneToOne(mappedBy = "groupMembersRoles")
    private GroupMember groupMembersRoles;
	
	
	
	public Long getId() {
		return id;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
