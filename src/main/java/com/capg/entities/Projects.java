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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
public class Projects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "project_id")
	private Long id;
	
	private String name;
	private String description;
	private LocalDateTime beginingDate;
	private LocalDateTime endDate;
	private int placeNumber;
	
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "divisions_id", nullable = false)
	private Divisions divisions;
	
	protected Projects () {}
	
	

}
