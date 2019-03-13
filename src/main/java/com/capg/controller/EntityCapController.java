package com.capg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
 *         Rest Controller for managing User
 */
@RestController
@RequestMapping("/api/entities")
public class EntityCapController {

	@Autowired
	EntityCapRepository entityCapRepository;

	/**
	 * GET:/api/entities:/id
	 * 
	 * @param id
	 * @return response entity with status 200 (ok) and entity on body
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getEntityCap(@PathVariable Long id) {
		Optional<EntityCap> entity = entityCapRepository.findById(id);
		if (!entity.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(entity);
	}
	/**
	 * GET:/api/entities
	 * 
	 * @return List all Entities
	 */
	@GetMapping
	public List<EntityCap> getAllEntities() {
		return entityCapRepository.findAll();
	}

	/**
	 * Post : Create new Entity
	 * 
	 * @param entityCap to entity to create
	 * @return create entity and responseEntiy with status 201
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody EntityCap entityCap) {
		if (entityCapRepository.findByName(entityCap.getName()) != null) {
			return new ResponseEntity<String>("Cette entité existe déja", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<EntityCap>(entityCapRepository.save(entityCap), HttpStatus.CREATED);
	}

	/**
	 * PUT: api/entities
	 * 
	 * @param entity the entity to update
	 * @return reponse entity with status 200 and the body
	 */
	@PutMapping
	public ResponseEntity<?> update(@RequestBody EntityCap entity) {
		if (entity.getId() == null)
			return new ResponseEntity<String>("Entité inexistante", HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(entityCapRepository.save(entity), HttpStatus.OK);

	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable Long id) {
		Optional<EntityCap> entity = entityCapRepository.findById(id);
		if (!entity.isPresent()) {
			return new ResponseEntity<>("Cette entité n'existe pas", HttpStatus.NOT_FOUND);
		} else {
			entityCapRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}

	}

}
