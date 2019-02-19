package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.EntityCap;

public interface EntityCapRepository extends JpaRepository<EntityCap, Long>{

	EntityCap findByName(String name);

}
