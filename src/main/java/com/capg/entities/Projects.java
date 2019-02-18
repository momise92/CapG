package com.capg.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Projects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "project_id")
	private Long id;
	
	private String name;
	
	@Column(columnDefinition = "TEXT", length= 255)
	private String description;
	
	@Column(name="beginning_date")
	private LocalDateTime beginningDate;
	
	@Column(name="end_date")
	private LocalDateTime endDate;
	
	@Column(name="place_number")
	private int placeNumber;
	
	@OneToMany
	private Set<Events> events = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "divisions_id", nullable = false)
	private Divisions divisions;
	
	protected Projects () {}
	
	public Projects(Long id, String name, String description, LocalDateTime beginningDate, LocalDateTime endDate,
			int placeNumber, City city, Divisions divisions) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.placeNumber = placeNumber;
		this.city = city;
		this.divisions = divisions;
	}

}
