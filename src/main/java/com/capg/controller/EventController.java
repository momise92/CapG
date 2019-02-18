package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.dao.EventRepository;
import com.capg.entities.Event;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 * Rest Controller for managing Events
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

}
