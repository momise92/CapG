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

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 *         Rest Controller for managing City
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	/**
	 * Get: api/cities get All cities
	 * 
	 * @return All cities
	 */
	@GetMapping
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	/**
	 * GET api/cities/:id : Show one city by his id
	 * 
	 * @param id the id of city to show
	 * @return
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getCity(@PathVariable Long id) {
		Optional<City> city = cityRepository.findById(id);
		if (!city.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(city);

	}

	/**
	 * POST / city/
	 * 
	 * @param city to city to create
	 * @return new city create and response entity with status 201
	 */
	@PostMapping
	public ResponseEntity<?> saveCity(@RequestBody City city) {
		if (cityRepository.findByName(city.getName()) != null) {
			return new ResponseEntity<String>("Cette ville existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<City>(cityRepository.save(city), HttpStatus.CREATED);
	}

	/**
	 * @param city
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> updateCity(@RequestBody City city) {
		if (city.getId() == null) {
			return new ResponseEntity<String>("Ville inexistante", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(cityRepository.save(city), HttpStatus.OK);
		}

	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable Long id) {
		Optional<City> city = cityRepository.findById(id);
		if (!city.isPresent()) {
			return new ResponseEntity<String>("Cette ville n'existe pas", HttpStatus.NOT_FOUND);
		} else {
			cityRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}

	}
}
