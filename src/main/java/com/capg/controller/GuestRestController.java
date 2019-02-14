package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CityRepository;
import com.capg.dao.RolesRepository;
import com.capg.dao.UsersRepository;
import com.capg.entities.City;
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
	
	@PostMapping(value = "/user")
	public Users save(@RequestBody Users user) {
		Roles defaultRole = rolesRepository.findByNameRole("espero");
		City yourCity = cityRepository.findByName(user.getCity().getName());
		user.setRole(defaultRole);
		user.setCity(yourCity);
		return userRepository.save(user);
	}

}
