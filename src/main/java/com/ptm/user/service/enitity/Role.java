package com.ptm.user.service.enitity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_privileges", 
	joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;

	public Role() {
		
	}
	

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", users=" + users + ", privileges=" + privileges + "]";
	}
	
	
}
