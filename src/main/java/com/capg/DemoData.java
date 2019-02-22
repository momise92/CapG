package com.capg;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.dao.CityRepository;
import com.capg.dao.EntityCapRepository;
import com.capg.dao.EventRepository;
import com.capg.dao.ProjectRepository;
import com.capg.dao.RoleAppRepository;
import com.capg.dao.UserAppRepository;
import com.capg.entities.City;
import com.capg.entities.EntityCap;
import com.capg.entities.Event;
import com.capg.entities.Project;
import com.capg.entities.RoleApp;
import com.capg.entities.UserApp;

@Component
public class DemoData {

	@Autowired
	RoleAppRepository roleAppRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	EntityCapRepository entityCapRepository;

	@Autowired
	UserAppRepository userAppRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EventRepository eventRepository;

	public void data() {

		RoleApp association = new RoleApp(null, "Association");
		roleAppRepository.save(association);

		RoleApp personnelManager = new RoleApp(null, "salarie");
		roleAppRepository.save(personnelManager);

		City sarcelles = new City(null, "Sarcelles");
		cityRepository.save(sarcelles);

		City puteaux = new City(null, "Puteaux");
		cityRepository.save(puteaux);

		EntityCap apps = new EntityCap(null, "Apps");
		entityCapRepository.save(apps);

		EntityCap finance = new EntityCap(null, "finance");
		entityCapRepository.save(finance);

		UserApp user1 = new UserApp(null, "Hawa", "Gaye", "Gaye@gmail.com", "password", sarcelles, apps);
		user1.setRole(association);
		userAppRepository.save(user1);

		UserApp user2 = new UserApp(null, "Moise", "Coulanges", "Moise@gmail.com", "password", puteaux, finance);
		user2.setRole(personnelManager);
		userAppRepository.save(user2);

		Project apiterra = new Project(null, "Apiterra",
				"Apiterra cherche des collaborateurs à Suresnes pour s’impliquer dans le club apicole, une fois par mois",
				LocalDateTime.now(), LocalDateTime.of(2019, 03, 19, 9, 15));
		projectRepository.save(apiterra);
		
		Event championsLeague = new Event(null, "Champions League", "Le match de l'année venez nombreux",
				new Date(2019, 10, 10), null, 0, puteaux, apps);
		championsLeague.setProject(apiterra);
		eventRepository.save(championsLeague);
		
		Event ffa = new Event(null, "Championnat de France", "Le championnat de l'année venez nombreux",
			new java.util.Date(), null, 0, puteaux, apps);
		eventRepository.save(ffa);
		
	Project capSurLeCode = new Project(null, "Cap sur le Code",
				"l’association Innov’Avenir organise une matinée à Suresnes et à Lille pour initier des enfants de 8 à 14 ans",
				LocalDateTime.now(), LocalDateTime.of(2019, 03, 19, 9, 15));
		projectRepository.save(capSurLeCode);
		
		user2.addEvent(ffa);
		userAppRepository.save(user2);

	}

}
