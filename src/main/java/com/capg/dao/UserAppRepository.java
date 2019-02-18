package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp, Long>{
	UserApp findByName(String name);
	UserApp findByEmail(String email);
	UserApp findByCity(String city);
	UserApp findByDivision(String division);

}
