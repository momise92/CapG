package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}