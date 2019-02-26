package com.capg.entities;

import java.util.HashSet;
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
	
	@Column(name = "city_name", nullable = false, length = 50, unique=true)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="city")
	private Set<UserApp> listUsers = new HashSet<>();
	
/*	@OneToMany(mappedBy = "city")
	@JsonIgnore
	@Column(name = "list_events")
	private List<Event> listEvents = new ArrayList<>();*/
	
	public City() {}

	public City(String name) {
		this.name = name;
	}
	
	
	
	

}
