package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.RoleApp;

public interface RoleAppRepository extends JpaRepository<RoleApp, Long> {
	RoleApp findByNameRole(String name);

}
