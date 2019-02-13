package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
