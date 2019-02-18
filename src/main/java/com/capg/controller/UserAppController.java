package com.capg.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CityRepository;
import com.capg.dao.DivisionRepository;
import com.capg.dao.RoleAppRepository;
import com.capg.dao.UserAppRepository;
import com.capg.entities.UserApp;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 * Rest Controller for managing User
 */
@RestController
@RequestMapping("/api")
public class UserAppController {

	@Autowired
	UserAppRepository userAppRepository;
	@Autowired
	RoleAppRepository roleAppRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	DivisionRepository divisionRepository;

	/**
	 * Get : get All Users
	 * 
	 * @return All Users
	 */
	@GetMapping(value = "/users")
	public List<UserApp> getAllUSers() {
		return userAppRepository.findAll();
	}

	/**
	 * Post : Create user
	 * 
	 * @param user the user to create
	 * @return The responseEntity with status 201 with body the new user
	 */
	@PostMapping(value = "/users")
	public ResponseEntity<?> save(@RequestBody UserApp user) {
		user.setRole(roleAppRepository.findByNameRole("Association"));
		user.setCity(cityRepository.findByName(user.getCity().getName()));
		user.setDivision(divisionRepository.findByName(user.getDivision().getName()));
		user.setCreatedDate(LocalDateTime.now());
		return new ResponseEntity<UserApp>(userAppRepository.save(user), HttpStatus.CREATED);
	}

}
