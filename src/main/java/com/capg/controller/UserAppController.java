package com.capg.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.capg.dto.UpdateUser;
import com.capg.dto.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.capg.dto.PasswordChangeDto;
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
	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * GET /users : All Users
	 * 
	 * @return the ResponseEntity with status 200 (OK) and the list of user in body
	 */
	@GetMapping
	public List<UserApp> getAllUsers() {
		return userAppRepository.findAll();
	}

	/**
	 * GET /users/:id : Show one user by his id
	 * 
	 * @param id the id of user to show
	 * @return ResponseEntitu with status 200 (OK) and one user in body
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<UserApp> user = userAppRepository.findById(id);
		if (!user.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(user);

	}

	@GetMapping("/current")
	public ResponseEntity<UserApp> getCurrentUser() {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		UserApp user = userAppRepository.findByEmail(name);

		return ResponseEntity.ok().body(user);

	}

	/**
	 * Post /users : Create user
	 * 
	 * @param user the user to create
	 * @return The responseEntity with status 201 with body the new user
	 */
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserVM user) {
		if (userAppRepository.existsByEmail(user.getEmail()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("user already exist");

		if (!user.getPassword().equals(user.getCheckPassword()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Please check your password");

		if (user.getCreatedDate() == null)
			user.setCreatedDate(LocalDateTime.now());

		UserApp newUser = new UserApp();
		newUser.setName(user.getName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setCreatedDate(user.getCreatedDate());
		newUser.setStatus(roleAppRepository.findByNameStatus(user.getStatus()));
		newUser.setCity(cityRepository.findByName(user.getCity()));
		newUser.setEntityCap(entityCapRepository.findByName(user.getEntityCap()));

		return ResponseEntity.ok(userAppRepository.save(newUser));

	}

	@PostMapping(value = "/password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeDto password) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		UserApp user = userAppRepository.findByEmail(name);

		if (!passwordEncoder.matches(password.getCurrentPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Your current password is incorrect");
		}
		String encryptedPassword = passwordEncoder.encode(password.getNewPassword());
		user.setPassword(encryptedPassword);

		return ResponseEntity.ok(userAppRepository.save(user));
	}

	/**
	 * PUT /users : Update an existing user
	 * 
	 * @param user to user to update
	 * @return response entity ok
	 */
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UpdateUser user) {
		Optional<UserApp> userToUpdate = userAppRepository.findById(user.getId());
		if (user.getId() == null || !userToUpdate.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

		UserApp updateUser = new UserApp();

		updateUser.setCity(cityRepository.findByName(user.getCity().getName()));
		updateUser.setEntityCap(entityCapRepository.findByName(user.getEntityCap().getName()));
		updateUser.setLastUpdate(LocalDateTime.now());
		updateUser.setCreatedDate(userToUpdate.get().getCreatedDate());
		updateUser.setStatus(roleAppRepository.findByNameStatus(user.getStatus().getNameStatus()));
		return new ResponseEntity<>(userAppRepository.save(updateUser), HttpStatus.OK);

	}

	/**
	 * DELETE /users/:id
	 * 
	 * @param id the id of user to delete
	 * @return ResponseEntity with status 200(OK)
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Optional<UserApp> user = userAppRepository.findById(id);
		if (!user.isPresent()) {
			return new ResponseEntity<>("Cette utilisateur n'existe pas", HttpStatus.NOT_FOUND);
		} else {
			entityCapRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

}
