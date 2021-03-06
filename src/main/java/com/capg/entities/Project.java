package com.capg.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "project_id")
	private Long id;

	@Column(name = "project_name", unique = true)
	private String name;

	@Column(columnDefinition = "TEXT", length = 255)
	private String description;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	/*
	 * @OneToMany( mappedBy = "project")
	 * 
	 * @JsonIgnoreProperties("project") private Set<Event> events = new
	 * HashSet<Event>();
	 */

	public Project() {
	}

	public Project(String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/*
	 * public void addEvent(Event event) { events.add(event);
	 * event.setProject(this); }
	 * 
	 * public void removeEvent(Event event) { this.events.remove(event);
	 * event.setProject(null); }
	 * 
	 * public List<Event>getEvents(){ return new ArrayList<Event>(events); }
	 */

}
