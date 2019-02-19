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
import com.capg.dao.EventRepository;
import com.capg.entities.Event;

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

}
