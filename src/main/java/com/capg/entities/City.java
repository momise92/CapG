package com.capg.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "city_id")
	private Long id;
	
	@Column(name = "city_name", nullable = false, length = 50)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="city")
	private Set<Users> listUsers = new HashSet<>();
	
	@OneToMany(mappedBy = "city")
	@JsonIgnore
	private List<Projects> listProjects = new ArrayList<>();
	
	public City() {}

	public City(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
	

}
