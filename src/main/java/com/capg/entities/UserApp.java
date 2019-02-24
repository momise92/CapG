package com.capg.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class UserApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "user_id")
	private Long id;
	
	@Column(nullable = false)
	@Size(min=3, max= 30)
	private String name;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Email
	@Column(nullable=false, unique=true)
	private String email;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@NotEmpty
	private String password;
	
	//@Pattern(regexp="(^$|[0-9]{10})")
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
	@JoinColumn(name = "entity_id")
	private EntityCap entityCap;

	@Column(nullable=true)
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private RoleApp Status;
	
	@ManyToMany
	@JoinTable(name = "users_subscribe_events", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "event_id"))
	private Set<Event> eventsSubscribe = new HashSet<>();
	
	@OneToMany(mappedBy="owner")
	private Set<Event> createdEvents = new HashSet<>();
	
	protected UserApp() {}

	public UserApp(Long id, String name, String lastName, String email, String password, City city, EntityCap entityCap) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.entityCap = entityCap;
	}
	public void addEvent(Event event)
	{
		eventsSubscribe.add(event);
	}
	
	public void removeEvent(Event event)
	{
		this.eventsSubscribe.remove(event);
	}
	
	public List<Event>getEvents(){
	return new ArrayList<Event>(eventsSubscribe);
	}
	
}
