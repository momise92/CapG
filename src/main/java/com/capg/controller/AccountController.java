package com.capg.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.CityRepository;
import com.capg.dao.EntityCapRepository;
import com.capg.dao.RoleAppRepository;
import com.capg.dao.UserAppRepository;
import com.capg.dto.JsonCredential;
import com.capg.dto.UserDto;
import com.capg.entities.RoleApp;
import com.capg.entities.UserApp;
import com.capg.security.JWTService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	UserAppRepository userAppRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleAppRepository roleAppRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	EntityCapRepository entityCapRepository;

	@Autowired
	JWTService jwtService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody JsonCredential cred, HttpServletRequest request)
			throws Exception {

		if (!userAppRepository.existsByEmail(cred.getEmail())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bad user");
		}

		UserApp user = userAppRepository.findByEmail(cred.getEmail());

		if (!user.isActive()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Disabled");
		}

		if (!passwordEncoder.matches(cred.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bad password");
		}

		RoleApp roles = user.getStatus();

		String jwt = jwtService.createJWT(user.getEmail(), roles);

		return ResponseEntity.ok().body(jwt);

	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserDto user) {
		if (userAppRepository.existsByEmail(user.getEmail()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("user already exist");

		if (!user.getPassword().equals(user.getCheckPassword()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Please check your password");

		UserApp newUser = new UserApp();
		newUser.setName(user.getName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setCreatedDate(LocalDateTime.now());
		newUser.setStatus(roleAppRepository.findByNameStatus(user.getRole()));
		newUser.setCity(cityRepository.findByName(user.getCity()));
		newUser.setEntityCap(entityCapRepository.findByName(user.getEntityCap()));

		return ResponseEntity.ok(userAppRepository.save(newUser));

	}

	@GetMapping("/me")
	public ResponseEntity<UserApp> me() {
		/*SecurityContextHolder.getContext().getAuthentication().getName();*/
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		UserApp user = userAppRepository.findByEmail(name);

		return ResponseEntity.ok(user);

	}
		
		

}
