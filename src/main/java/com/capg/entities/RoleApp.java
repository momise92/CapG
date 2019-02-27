package com.capg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class RoleApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "name_statut", unique=true)
	@Size(min= 3, max= 50)
	private String nameStatus;
	
	public RoleApp () {}

	public RoleApp(@Size(min = 3, max = 50) String nameStatus) {
		this.nameStatus = nameStatus;
	}
	

}
