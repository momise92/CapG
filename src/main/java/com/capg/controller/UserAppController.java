package com.capg.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CityRepository;
import com.capg.dao.EntityCapRepository;
import com.capg.dao.RoleAppRepository;
import com.capg.dao.UserAppRepository;
import com.capg.entities.UserApp;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 *         Rest Controller for managing User
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserAppController {

	@Autowired
	UserAppRepository userAppRepository;
	@Autowired
	RoleAppRepository roleAppRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	EntityCapRepository entityCapRepository;

	/**
	 * GET /users : All Users
	 * 
	 * @return the ResponseEntity with status 200 (OK) and the list of user in body
	 */
	@GetMapping
	public List<UserApp> getAllUSers() {
		return userAppRepository.findAll();
	}

	/**
	 * GET /users/:id : Show one user by his id
	 * 
	 * @param id the id of user to show
	 * @return
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneCity(@PathVariable Long id) {
		Optional<UserApp> user = userAppRepository.findById(id);
		if (!user.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(user);

	}

	/**
	 * Post /users : Create user
	 * 
	 * @param user the user to create
	 * @return The responseEntity with status 201 with body the new user
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserApp user) {
		user.setRole(roleAppRepository.findByNameRole("Association"));
		user.setCity(cityRepository.findByName(user.getCity().getName()));
		user.setEntityCap(entityCapRepository.findByName(user.getEntityCap().getName()));
		user.setCreatedDate(LocalDateTime.now());
		return new ResponseEntity<UserApp>(userAppRepository.save(user), HttpStatus.CREATED);
	}

	/**
	 * PUT /users : Update an existing user
	 * 
	 * @param user to user to update
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> updateUserApp(@RequestBody UserApp user) {
		if (user.getId() == null)
			throw new RuntimeException("Invalid id");

		user.setCity(cityRepository.findByName(user.getCity().getName()));
		user.setEntityCap(entityCapRepository.findByName(user.getEntityCap().getName()));
		user.setLastUpdate(LocalDateTime.now());
		Optional<UserApp> userCreateDate = userAppRepository.findById(user.getId());
		user.setCreatedDate(userCreateDate.get().getCreatedDate());
		user.setRole(roleAppRepository.findByNameRole(user.getRole().getNameRole()));
		return new ResponseEntity<UserApp>(userAppRepository.save(user), HttpStatus.OK);

	}

	/**
	 * DELETE /users/:id
	 * 
	 * @param id the id of user to delete
	 * @return ResponseEntity with status 200(OK)
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserApp(@PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (userAppRepository.findById(id) == null) {
			return new ResponseEntity<String>("Ce user n'existe pas", HttpStatus.NOT_FOUND);
		}

		try {
			userAppRepository.deleteById(id);
			result = new ResponseEntity<>(HttpStatus.OK);
		}

		catch (Exception ex) {

			result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;

	}

}
