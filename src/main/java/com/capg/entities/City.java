package com.capg.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="city")
	private Set<Users> listUsers = new HashSet<>();
	
	@OneToMany(mappedBy = "city")
	private List<Projects> listProjects = new ArrayList<>();
	
	public City() {}

	public City(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
	

}
