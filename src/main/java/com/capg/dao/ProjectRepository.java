package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project findByName(String name);

	Project deleteByName(String name);

}
