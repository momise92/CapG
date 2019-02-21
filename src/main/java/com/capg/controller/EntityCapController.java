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
import com.capg.dao.EntityCapRepository;
import com.capg.entities.EntityCap;

/**
 * @author Moïse Coulanges
 * @author Hawa Gaye
 * 
 * Rest Controller for managing User
 */
@RestController
@RequestMapping("/api/")
public class EntityCapController {
	
	@Autowired
	EntityCapRepository entityCapRepository;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> getOneEntityCap(@PathVariable Long id) {
			
			if (!entityCapRepository.findById(id).isPresent()) {
	            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
	        } else {
	        return new ResponseEntity<>(entityCapRepository.findById(id), HttpStatus.OK);
	        }
	}
	/**
	 * Get : 
	 * @return List all Entities
	 */
	@GetMapping(value = "/entities")
	public List<EntityCap> getAllEntities() {
		return entityCapRepository.findAll();
	}
	
	/**
	 * Post : Create new Entity
	 * @param entityCap to entity to create
	 * @return create entity and responseEntiy with status 201
	 */
	@PostMapping(value = "/entities")
	public ResponseEntity<?> save(@RequestBody EntityCap entityCap) {
		if (entityCapRepository.findByName(entityCap.getName()) != null) {
			return new ResponseEntity<String>("Cette entité existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<EntityCap>(entityCapRepository.save(entityCap), HttpStatus.CREATED);
	}
	
	//Delete one entityCap
		@DeleteMapping("/entities/{id}")
		public ResponseEntity<?> deleteProject(@PathVariable Long id) {
			ResponseEntity<?> result = null;

			if (entityCapRepository.findById(id) == null) {
				return new ResponseEntity<String>("Ce projet n'existe passssssss", HttpStatus.NOT_FOUND);}

			try {
				entityCapRepository.deleteById(id);
				result = new ResponseEntity<>(true,HttpStatus.OK);
				}

			catch (Exception ex) {

				result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);}

				return result;

		}
		

		@PutMapping(value = "/entities")
		public ResponseEntity<?> update(@RequestBody EntityCap entity) {
			if (entity.getId() == null) return new ResponseEntity<String>("Entité inexistante", HttpStatus.NOT_FOUND);
			
				return new ResponseEntity<>(entityCapRepository.save(entity), HttpStatus.OK);
			
		}

}
