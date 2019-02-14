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

	import com.capg.dao.DivisionsRepository;
	import com.capg.dao.ProjectsRepository;
	import com.capg.dao.UsersRepository;
	import com.capg.entities.Divisions;
	import com.capg.entities.Projects;
	import com.capg.entities.Users;

	@RestController
	@RequestMapping("/manager")
	public class ManagerController {

	
		
		@Autowired
		UsersRepository usersRepository;
		
		@GetMapping(value = "/users")
		public List<Users> getAllUSers() {
			return usersRepository.findAll();
		}
		
		
		@Autowired
		ProjectsRepository projectsRepository;
		
		@GetMapping(value = "/projects")
		public List<Projects> getAllProjects(){
			return projectsRepository.findAll ();
		}
		
		
}
