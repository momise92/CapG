package com.capg.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "event_id")
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
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "division_id", nullable = false)
	private Division division;

	public Event(Long id, String name, String description, LocalDateTime beginningDate, LocalDateTime endDate,
			int placeNumber, City city, Division division) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.placeNumber = placeNumber;
		this.city = city;
		this.division = division;
	}
	
	
}
