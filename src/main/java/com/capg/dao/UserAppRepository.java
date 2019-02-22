package com.capg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.capg.entities.UserApp;

@CrossOrigin("*")
public interface UserAppRepository extends JpaRepository<UserApp, Long>{
	UserApp findByName(String name);
	UserApp findByEmail(String email);
	UserApp findByCity(String city);
	UserApp findByEntityCap(String entity);
	List<UserApp> findByIsActiveFalseOrderByNameAsc();

}
