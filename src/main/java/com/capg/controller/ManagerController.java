package com.capg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CitiesRepository;
import com.capg.dao.DivisionsRepository;
import com.capg.dao.EventsRepository;
import com.capg.dao.ProjectsRepository;
import com.capg.dao.UsersRepository;
import com.capg.entities.City;
import com.capg.entities.Divisions;
import com.capg.entities.Events;
import com.capg.entities.Projects;
import com.capg.entities.Users;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ProjectsRepository projectsRepository;

	@Autowired
	CitiesRepository citiesRepository;

	@Autowired
	DivisionsRepository divisionsRepository;
	
	@Autowired
	EventsRepository eventsRepository;

	@GetMapping(value = "/users")
	public List<Users> getAllUSers() {
		return usersRepository.findAll();
	}

	@GetMapping(value = "/projects")
	public List<Projects> getAllProjects() {
		return projectsRepository.findAll();
	}
	
	@GetMapping(value = "/events")
	public List<Events> getAllEvents() {
		return eventsRepository.findAll();
	}

	@GetMapping(value = "/cities")
	public List<City> getAllCity() {
		return citiesRepository.findAll();
	}

	@GetMapping(value = "/divisions")
	public List<Divisions> getAllDivisions() {
		return divisionsRepository.findAll();
	}

	// Manager peut ajouter une ville
	@PostMapping(value = "/city")
	public ResponseEntity<?> save(@RequestBody City city) {
		if (citiesRepository.findByName(city.getName()) != null) {
			return new ResponseEntity<String>("Cette ville existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<City>(citiesRepository.save(city), HttpStatus.CREATED);
	}

	// Manager peut ajouter une division
	@PostMapping(value = "/division")
	public ResponseEntity<?> save(@RequestBody Divisions division) {
		if (divisionsRepository.findByName(division.getName()) != null) {
			return new ResponseEntity<String>("Cette entité existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Divisions>(divisionsRepository.save(division), HttpStatus.CREATED);
	}

}
