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
public class EntityCap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "entity_id")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="entityCap")
	@JsonIgnore
	private Set<UserApp> listusers = new HashSet<>();
	
	@OneToMany(mappedBy = "entityCap")
	@JsonIgnore
	@Column(name = "list_events")
	private List<Event> listEvents = new ArrayList<>();
			
	
	protected EntityCap () {}


	public EntityCap(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	

}
