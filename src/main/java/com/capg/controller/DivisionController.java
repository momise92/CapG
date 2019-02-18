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

import com.capg.dao.DivisionRepository;
import com.capg.entities.Division;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 * Rest Controller for managing User
 */
@RestController
@RequestMapping("/api/")
public class DivisionController {
	
	@Autowired
	DivisionRepository divisionRepository;
	
	
	/**
	 * Get : 
	 * @return List all divisions
	 */
	@GetMapping(value = "/divisions")
	public List<Division> getAllDivisions() {
		return divisionRepository.findAll();
	}
	
	/**
	 * Post : Create new division
	 * @param division to division to create
	 * @return create division and responseEntiy with status 201
	 */
	@PostMapping(value = "/divisions")
	public ResponseEntity<?> save(@RequestBody Division division) {
		if (divisionRepository.findByName(division.getName()) != null) {
			return new ResponseEntity<String>("Cette entité existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Division>(divisionRepository.save(division), HttpStatus.CREATED);
	}

}
