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

import com.capg.dao.CityRepository;
import com.capg.entities.City;
import com.capg.entities.UserApp;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 *         Rest Controller for managing City
 */
@RestController
@RequestMapping("/api/city")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	/**
	 * Get : get All cities
	 * 
	 * @return All cities
	 */
	@GetMapping
	public List<City> getAllCity() {
		return cityRepository.findAll();
	}
	
	
	/**
	 * GET /city/:id : Show one city by his id
	 * 
	 * @param id the id of city to show
	 * @return 
	 * @return 
	 */
	@GetMapping(value="/{id}")
	public ResponseEntity<?> getOneCity(@PathVariable Long id) {
			
			if (!cityRepository.findById(id).isPresent()) {
	            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
	        } else {
	        return new ResponseEntity<>(cityRepository.findById(id), HttpStatus.OK);
	        }
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

	@PutMapping
	public ResponseEntity<?> update(@RequestBody City city) {
		if (city.getId() == null) {
			return new ResponseEntity<String>("Ville inexistante", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(cityRepository.save(city), HttpStatus.OK);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (!cityRepository.findById(id).isPresent()) {
			return new ResponseEntity<String>("Cette ville n'existe pas", HttpStatus.NOT_FOUND);
		}

		try {
			cityRepository.deleteById(id);
			result = new ResponseEntity<>(HttpStatus.OK);
		}

		catch (Exception ex) {

			result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;

	}

}
