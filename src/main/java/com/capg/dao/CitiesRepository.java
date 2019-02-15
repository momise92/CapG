package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.City;

public interface CitiesRepository extends JpaRepository<City, Long> {
	City findByName(String name);

}
