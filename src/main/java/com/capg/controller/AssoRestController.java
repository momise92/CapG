package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.UsersRepository;
import com.capg.entities.Users;

@RestController
@RequestMapping("/association")
public class AssoRestController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping(value = "/users")
	public List<Users> getAllUSers() {
		return usersRepository.findAll();
	}

}
