package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Events;

public interface EventsRepository extends JpaRepository<Events, Long>{

}
