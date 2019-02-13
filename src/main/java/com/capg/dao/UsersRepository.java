package com.capg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

}
