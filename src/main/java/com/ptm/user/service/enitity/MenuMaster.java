package com.ptm.user.service.enitity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Setter;

import lombok.Getter;

@Entity
@Table(name = "menu_master")
@Getter
@Setter
public class MenuMaster extends AbstractAuditingEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 50)
	@Column(name = "menu_name", length = 50)
	private String menuName;
	
	@Column(name = "status")
	private String status;

	

}
