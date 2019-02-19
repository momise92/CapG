package com.capg.controller;

import java.util.List;

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
import com.capg.dao.EventRepository;
import com.capg.entities.Event;
import com.capg.entities.Project;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 *         Rest Controller for managing Events
 */
@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	EventRepository eventRepository;

	@GetMapping(value = "/events")
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@PostMapping(value = "/events")
	public ResponseEntity<?> save(@RequestBody Event event) {
		if (eventRepository.findByName(event.getName()) != null) {
			return new ResponseEntity<String>("Ce nom d'événement existe déja", HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Event>(eventRepository.save(event), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/events")
	public ResponseEntity<?> update(@RequestBody Event event) {
		if (event.getId() == null) return new ResponseEntity<String>("Evenement inexistant", HttpStatus.NOT_FOUND);
		
			return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/events/{id}")
	public ResponseEntity<?> deleteEvents(@PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (eventRepository.findById(id) == null) {
			return new ResponseEntity<String>("Ce projet n'existe pas", HttpStatus.NOT_FOUND);
		}

		try {
			eventRepository.deleteById(id);
			result = new ResponseEntity<>(HttpStatus.OK);
		}

		catch (Exception ex) {

			result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;

	}

}
