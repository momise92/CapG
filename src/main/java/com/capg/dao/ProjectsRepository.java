package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long>{

}
