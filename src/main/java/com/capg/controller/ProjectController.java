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
import com.capg.dao.DivisionRepository;
import com.capg.dao.ProjectRepository;
import com.capg.entities.Project;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 *Rest Controller for managing Projects
 */
@RestController
@RequestMapping("/api")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	DivisionRepository divisionRepository;

	/**
	 * GET : List of all projects
	 * 
	 * @return All Projects
	 */
	@GetMapping(value = "/projects")
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	/**
	 * Post : Create new project
	 *
	 * @param project to create
	 * @return Project created and ResponseEntity with status 201
	 */
	@PostMapping(value = "/projects")
	public ResponseEntity<?> save(@RequestBody Project project) {
		if (projectRepository.findByName(project.getName()) != null) {
			return new ResponseEntity<String>("Ce nom de projet existe déja", HttpStatus.CONFLICT);
		}

		/*City yourCity = cityRepository.findByName(project.getCity().getName());
		Division yourDivision = divisionRepository.findByName(project.getDivision().getName());
		project.setCity(yourCity);
		project.setDivision(yourDivision);*/
		return new ResponseEntity<Project>(projectRepository.save(project), HttpStatus.CREATED);
	}

	/**
	 * Delete : delete one project
	 * 
	 * @param id to the project
	 * @return responseEntity with status 404 if not exist, 200 if it's delete or Exception with status 500
	 */
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (projectRepository.findById(id) == null) {
			return new ResponseEntity<String>("Ce projet n'existe pas", HttpStatus.NOT_FOUND);
		}

		try {
			projectRepository.deleteById(id);
			result = new ResponseEntity<>(HttpStatus.OK);
		}

		catch (Exception ex) {

			result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;

	}

}
