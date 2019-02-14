package com.capg;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capg.dao.CityRepository;
import com.capg.dao.DivisionsRepository;
import com.capg.dao.ProjectsRepository;
import com.capg.dao.RolesRepository;
import com.capg.dao.UsersRepository;
import com.capg.entities.City;
import com.capg.entities.Divisions;
import com.capg.entities.Projects;
import com.capg.entities.Roles;
import com.capg.entities.Users;

@SpringBootApplication
public class CapGApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CapGApplication.class, args);
	}
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	DivisionsRepository DivisionsRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	ProjectsRepository projectsRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Roles association = new Roles(null, "Moncul");
		rolesRepository.save(association);
		
		Roles personnelManager = new Roles(null, "Association");
		rolesRepository.save(personnelManager);
		
		
		
		City sarcelles = new City(null, "Sarcelles");
		cityRepository.save(sarcelles);
		
		City dakar = new City(null, "Dakar");
		cityRepository.save(dakar);
		
		
		
		Divisions apps = new Divisions(null, "Apps");
		DivisionsRepository.save(apps);

		Divisions finance = new Divisions(null, "finance");
		DivisionsRepository.save(finance);
		

		
		Users user1 = new Users(null, "Hawa", "Gaye", "Gaye@gmail.com", "password",sarcelles, 
				apps, association);
		usersRepository.save(user1);
		
		Users user2 = new Users(null, "Moise", "Coulanges", "Moise@gmail.com", "password",dakar, 
				finance, personnelManager);
		usersRepository.save(user2);
		
		
		
		Projects apiterra = new Projects(null, "Apiterra", "cherche des collaborateurs à Suresnes pour s’impliquer dans le club apicole, une fois par mois",
				LocalDateTime.now(), LocalDateTime.of(2019, 03, 19, 9, 15), 3, sarcelles, apps);
		
		projectsRepository.save(apiterra);
		
		
		

		
	

		

	}

}

