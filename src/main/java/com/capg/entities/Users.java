package com.capg.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Email
	@Column(nullable=false, unique=true)
	private String email;
	
	@JsonIgnore
	@Size(min=8, max=30, message = "*Your password must have 8 characters")
	private String password;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "*Your number is incorrect")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private boolean isActive = true;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="last_update")
	private LocalDateTime lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "city_id", nullable=false)
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
