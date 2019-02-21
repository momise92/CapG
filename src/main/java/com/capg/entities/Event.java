package com.capg.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@Column(name="event_name", unique=true)
	@Size(min=3, max=50)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "beginning_date")
	private LocalDateTime beginningDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "place_number")
	private int placeNumber;
	
	@JsonIgnore
	@ManyToMany(mappedBy="events")
	private Set<UserApp> users = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","events"})
	private Project project;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@ManyToOne
	@JoinColumn(name = "entity_id", nullable = false)
	private EntityCap entityCap;

	public Event(Long id, String name, String description, LocalDateTime beginningDate, LocalDateTime endDate,
			int placeNumber, City city, EntityCap entityCap) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.placeNumber = placeNumber;
		this.city = city;
		this.entityCap = entityCap;
	}

/*	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Project))
			return false;
		return id != null && id.equals(((Project) o).getId());
	}

	@Override
	public int hashCode() {
		return (int) (31+this.id);
	}*/

}
