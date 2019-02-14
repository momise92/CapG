package com.capg.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CityRepository;
import com.capg.dao.DivisionsRepository;
import com.capg.dao.RolesRepository;
import com.capg.dao.UsersRepository;
import com.capg.entities.City;
import com.capg.entities.Divisions;
import com.capg.entities.Roles;
import com.capg.entities.Users;

@RestController
@RequestMapping("/guest")
public class GuestRestController {
	
	@Autowired
	UsersRepository userRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	DivisionsRepository divisionsRepository ;
	
	/*Inscription des users*/
	@PostMapping(value = "/user")
	public Users save(@RequestBody Users user) {
		Roles defaultRole = rolesRepository.findByNameRole("Association");
		City yourCity = cityRepository.findByName(user.getCity().getName());
		Divisions yourDivision = divisionsRepository.findByName(user.getDivisions().getName());
		user.setRole(defaultRole);
		user.setCity(yourCity);
		user.setDivisions(yourDivision);
		user.setCreatedDate(LocalDateTime.now());
		return userRepository.save(user);
	}
	
	@PutMapping(value = "/user")
	public Users update(@RequestBody Users user) {
		City yourCity = cityRepository.findByName(user.getCity().getName());
		Divisions yourDivision = divisionsRepository.findByName(user.getDivisions().getName());
		Roles defaultRole = rolesRepository.findByNameRole("Association");
		user.setCity(yourCity);
		user.setDivisions(yourDivision);
		user.setLastUpdate(LocalDateTime.now());
		user.setRole(defaultRole);
		return userRepository.save(user);
	}

}
