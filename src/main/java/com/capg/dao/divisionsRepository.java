package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Divisions;

public interface DivisionsRepository extends JpaRepository<Divisions, Long>{

	Divisions findByName(String name);

}
