package com.capg.controller;

import java.util.List;
import java.util.Optional;

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

import com.capg.dao.RoleAppRepository;
import com.capg.entities.RoleApp;
import com.capg.entities.UserApp;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 *         Rest Controller for managing Role
 */

@RestController
@RequestMapping("/api/roles")
public class RoleAppController {

	@Autowired
	RoleAppRepository roleAppRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> getOneRole(@PathVariable Long id) {
		Optional<RoleApp> role = roleAppRepository.findById(id);
		if (!role.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(role);

	}

	@GetMapping
	public List<RoleApp> getAllRoleApp() {
		return roleAppRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody RoleApp roleApp) {
		if (roleAppRepository.findByNameRole(roleApp.getNameRole()) != null) {
			return new ResponseEntity<String>("Ce nom de role existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<RoleApp>(roleAppRepository.save(roleApp), HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody RoleApp role) {
		if (role.getId() == null)
			return new ResponseEntity<String>("Role inexistant", HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(roleAppRepository.save(role), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoleApp(@PathVariable Long id) {
		Optional<RoleApp> role = roleAppRepository.findById(id);
		if (!role.isPresent())
		{
			return new ResponseEntity<String>("Ce role n'existe pas", HttpStatus.NOT_FOUND);
		}else {
			roleAppRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
			
		
		
	}
}
