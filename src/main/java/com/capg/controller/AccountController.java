package com.capg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dao.UserAppRepository;
import com.capg.dto.JsonCredential;
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
	JWTService jwtService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody JsonCredential cred, HttpServletRequest request) throws Exception {

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

}
