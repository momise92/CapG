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

import com.capg.dao.CityRepository;
import com.capg.entities.City;


/**
 * @author Moïse Coulanges 
 * @author Hawa Gaye
 * 
 * Rest Controller for managing City
 */
@RestController
@RequestMapping("/api/")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	/**
	 * Get : get All cities
	 * 
	 * @return All cities
	 */
	@GetMapping(value = "/city")
	public List<City> getAllCity() {
		return cityRepository.findAll();
	}

	/**
	 * @param city to city to create
	 * @return new city create and response entity with status 201
	 */
	@PostMapping(value = "/city")
	public ResponseEntity<?> save(@RequestBody City city) {
		if (cityRepository.findByName(city.getName()) != null) {
			return new ResponseEntity<String>("Cette ville existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<City>(cityRepository.save(city), HttpStatus.CREATED);
	}

}
