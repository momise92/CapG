package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Division;

public interface DivisionRepository extends JpaRepository<Division, Long>{

	Division findByName(String name);

}
