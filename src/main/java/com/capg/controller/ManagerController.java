package com.capg.controller;

	
	
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CityRepository;
import com.capg.dao.ProjectsRepository;
	import com.capg.dao.UsersRepository;
import com.capg.entities.City;
import com.capg.entities.Divisions;
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
		CityRepository citiesRepository;
		
		
		
		@GetMapping(value = "/users")
		public List<Users> getAllUSers() {
			return usersRepository.findAll();
		}
		
		@GetMapping(value = "/projects")
		public List<Projects> getAllProjects(){
			return projectsRepository.findAll ();
		}
		
		@GetMapping(value = "/cities")
		public List<City> getAllCity(){
			return citiesRepository.findAll ();
		}
		
		
		//Asso peut ajouter une ville
		@PostMapping(value = "/city")
		public ResponseEntity<?> save(@RequestBody City city) {
			if (citiesRepository.findByName(city.getName()) != null) {
				return new ResponseEntity<String>("Cette ville existe déja", HttpStatus.CONFLICT);
			}
			City newCity = citiesRepository.findByName(city.getName());
			return new ResponseEntity<City>(citiesRepository.save(city), HttpStatus.CREATED);
		}
		
		
}
