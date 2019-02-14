package com.capg.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "users_id")
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private boolean isActive = true;
	
	private LocalDateTime createdDate = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "divisions_id")
	private Divisions divisions;
	
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Roles role;
	
	protected Users() {}

	public Users(Long id, String name, String lastName, String email, String password, City city, Divisions divisions,
			Roles role) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.divisions = divisions;
		this.role = role;
	}
	
	
	
	
	
	

}
