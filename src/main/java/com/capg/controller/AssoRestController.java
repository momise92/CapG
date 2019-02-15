package com.capg.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.dao.CityRepository;
import com.capg.dao.DivisionsRepository;
import com.capg.dao.ProjectsRepository;
import com.capg.dao.RolesRepository;
import com.capg.dao.UsersRepository;
import com.capg.entities.City;
import com.capg.entities.Divisions;
import com.capg.entities.Projects;
import com.capg.entities.Users;



@RestController
@RequestMapping("/association")
public class AssoRestController {

	@Autowired
	UsersRepository usersRepository;
	@Autowired
	ProjectsRepository projectsRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	DivisionsRepository divisionsRepository;
	@Autowired
	RolesRepository rolesRepository;
	
	//Asso peut lister tous les Users//
	@GetMapping(value = "/users")
	public List<Users> getAllUSers() {
		return usersRepository.findAll();
	}
	
/*	Mise a jour users
	@PutMapping(value = "/user")
	public Users update(@RequestBody Users user) {
		user.setCity(cityRepository.findByName(user.getCity().getName()));
		user.setDivisions(divisionsRepository.findByName(user.getDivisions().getName()));
		user.setLastUpdate(LocalDateTime.now());
		user.setRole(rolesRepository.findByNameRole(user.getRole().getNameRole()));
		return usersRepository.save(user);
	}*/

	//Asso peut lister tous les projets
	@GetMapping(value = "/projects")
	public List<Projects> getAllProjects() {
		return projectsRepository.findAll();
	}

	//Asso peut ajouter projet
	@PostMapping(value = "/projects")
	public ResponseEntity<?> save(@RequestBody Projects projects) {
		if (projectsRepository.findByName(projects.getName()) != null) {
			return new ResponseEntity<String>("Ce nom de projet existe déja", HttpStatus.CONFLICT);
		}

		City yourCity = cityRepository.findByName(projects.getCity().getName());
		Divisions yourDivision = divisionsRepository.findByName(projects.getDivisions().getName());
		projects.setCity(yourCity);
		projects.setDivisions(yourDivision);
		return new ResponseEntity<Projects>(projectsRepository.save(projects), HttpStatus.CREATED);
	}
	
	//Asso peut supprimer un projet
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (projectsRepository.findById(id) == null) {
			return new ResponseEntity<String>("Ce projet n'existe passssssss", HttpStatus.NOT_FOUND);}

		try {
			projectsRepository.deleteById(id);
			result = new ResponseEntity<>(true,HttpStatus.OK);
			}

		catch (Exception ex) {

			result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);}

			return result;

	}
	
	

/*	@PutMapping
	public ResponseEntity<?> updateProject(@RequestBody Project project) {
		ResponseEntity<?> result = null;
		if (projects.findById(project.getId()) != null) {
			result = new ResponseEntity<String>("No project with id " + project.getId(), HttpStatus.NO_CONTENT);
		} else {
			result = new ResponseEntity<Project>(projects.save(project), HttpStatus.OK);
		}

		return result;

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getproject(@PathVariable Long id) {
		ResponseEntity<?> result = null;
		if (projects.findById(id).isPresent()) {
			result = new ResponseEntity<Project>(projects.findById(id).get(), HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("No project with id " + id, HttpStatus.NO_CONTENT);
		}
		return result;*/
		
	
	

}
