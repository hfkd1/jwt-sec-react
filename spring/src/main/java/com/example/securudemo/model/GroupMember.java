package com.example.securudemo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class GroupMember {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	public Long getId() {
		return id;
	}

	public Role getGroupMembersRoles() {
		return groupMembersRoles;
	}

	public void setGroupMembersRoles(Role groupMembersRoles) {
		this.groupMembersRoles = groupMembersRoles;
	}

	public Collection<User> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Collection<User> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public GroupMember(Role groupMembersRoles, Collection<User> groupMembers) {
		super();
		this.groupMembersRoles = groupMembersRoles;
		this.groupMembers = groupMembers;
	}

	@OneToOne
    @JoinTable(
        name = "groupMembersRoles", 
        joinColumns = @JoinColumn(
          name = "groupMemberId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "roleId", referencedColumnName = "id"))
	private Role groupMembersRoles;
	
	@ManyToMany
    @JoinTable(
        name = "groupMembers", 
        joinColumns = @JoinColumn(
          name = "groupMemberId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "userId", referencedColumnName = "id"))
	private Collection<User> groupMembers;
	
}
