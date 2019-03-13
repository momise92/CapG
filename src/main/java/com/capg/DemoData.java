package com.capg;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class DemoData implements ApplicationRunner {

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

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {


		RoleApp association = new RoleApp("association");
		roleAppRepository.save(association);
		RoleApp salarie = new RoleApp("salarie");
		roleAppRepository.save(salarie);
		RoleApp personnelManager = new RoleApp("rh");
		roleAppRepository.save(personnelManager);

		City sarcelles = new City("Sarcelles");
		cityRepository.save(sarcelles);
		City puteaux = new City("Puteaux");
		cityRepository.save(puteaux);
		City suresnes = new City("Suresnes");
		cityRepository.save(suresnes);
		City lille = new City("Lille");
		cityRepository.save(lille);
		City marseille = new City("Marseille");
		cityRepository.save(marseille);

		EntityCap apps = new EntityCap("Apps");
		entityCapRepository.save(apps);
		EntityCap finance = new EntityCap("FS");
		entityCapRepository.save(finance);
		EntityCap ATS = new EntityCap("Sogeti ATS");
		entityCapRepository.save(ATS);
		EntityCap sogeti = new EntityCap("Sogeti High-Tech");
		entityCapRepository.save(sogeti);



		UserApp user1 = new UserApp("Hawa", "Gaye", "Gaye@gmail.com", encoder.encode("password"), sarcelles, apps);
		user1.setStatus(association);
		userAppRepository.save(user1);

		UserApp user2 = new UserApp("Moise", "Coulanges", "Moise@gmail.com", encoder.encode("password"), puteaux, finance);
		user2.setStatus(personnelManager);
		userAppRepository.save(user2);

		Project apiterra = new Project("Apiterra",
				"Apiterra cherche des collaborateurs à Suresnes pour s’impliquer dans le club apicole, une fois par mois",
				LocalDateTime.now(), LocalDateTime.of(2019, 03, 19, 9, 15));
		projectRepository.save(apiterra);
		
		Event championsLeague = new Event("Champions League", "Le match de l'année venez nombreux",
				new java.util.Date(), null, 0, lille, apps);
		championsLeague.setProject(apiterra);
		eventRepository.save(championsLeague);
		
		Event ffa = new Event("Championnat de France", "Le championnat de l'année venez nombreux",
			new java.util.Date(), null, 0, puteaux, apps);
		eventRepository.save(ffa);
		
		Event capSurLeCode = new Event("Cap sur le Code", "l’association Innov’Avenir organise une matinée à Suresnes "
				+ "et à Lille pour initier des enfants de 8 à 14 ans",
				new java.util.Date(), null, 10, puteaux, apps);
		eventRepository.save(capSurLeCode);
		
		Event nettoyage = new Event("Nettoyage de plage ", "Capgemini organise un nettoyage de plage et de rivières partout en France et cherche "
				+ "des collaborateurs bénévoles pour participer à l’activité un samedi matin",
				new java.util.Date(), null, 10, sarcelles, apps);
		eventRepository.save(nettoyage);
		
		
		
		user2.addEvent(ffa);
		user1.addEvent(championsLeague);
		userAppRepository.save(user2);
		userAppRepository.save(user1);

	}
	
}
