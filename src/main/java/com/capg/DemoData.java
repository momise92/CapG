package com.capg;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.dao.CitiesRepository;
import com.capg.dao.DivisionsRepository;
import com.capg.dao.EventsRepository;
import com.capg.dao.ProjectsRepository;
import com.capg.dao.RolesRepository;
import com.capg.dao.UsersRepository;
import com.capg.entities.City;
import com.capg.entities.Divisions;
import com.capg.entities.Events;
import com.capg.entities.Projects;
import com.capg.entities.Roles;
import com.capg.entities.Users;

@Component
public class DemoData {
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	CitiesRepository citiesRepository;
	
	@Autowired
	DivisionsRepository DivisionsRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	ProjectsRepository projectsRepository;
	
	@Autowired
	EventsRepository eventsRepository;
	
	public void data() {
	
	Roles association = new Roles(null, "Moncul");
	rolesRepository.save(association);
	
	Roles personnelManager = new Roles(null, "Association");
	rolesRepository.save(personnelManager);
	
	
	
	City sarcelles = new City(null, "Sarcelles");
	citiesRepository.save(sarcelles);
	
	City dakar = new City(null, "Dakar");
	citiesRepository.save(dakar);
	
	
	
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
	
	
	Projects apiterra = new Projects(null, "Apiterra", "Apiterra cherche des collaborateurs à Suresnes pour s’impliquer dans le club apicole, une fois par mois", 
			LocalDateTime.now(), LocalDateTime.of(2019, 03, 19, 9, 15), 3, dakar, finance);
	projectsRepository.save(apiterra);
	
	Events championsLeague = new Events(null, "Champions League", "Le match de l'année venez nombreux", LocalDateTime.now(), 
			LocalDateTime.of(2019, 04, 19, 9, 15), 100, dakar, apps);
	championsLeague.setProject(apiterra);
	eventsRepository.save(championsLeague);
	
	
	}

}
