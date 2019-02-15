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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
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
	
	@ManyToMany(mappedBy="events")
	private Set<Projects> projects = new HashSet<Projects>();
	
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "divisions_id", nullable = false)
	private Divisions divisions;
}
