package com.capg;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CapGApplicationTests {

	@Autowired
	RoleAppRepository roleAppRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	EntityCapRepository EntityCapRepository;

	@Autowired
	UserAppRepository userAppRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EventRepository eventRepository;
	
	@Test
	public void contextLoads() {
		
		City dakar = new City(null, "Dakar");
		cityRepository.save(dakar);

		EntityCap apps = new EntityCap(null, "Apps");
		EntityCapRepository.save(apps);
		
	
		
	Project CapSurLeCode = new Project(null, "Cap sur le Code",
				"l’association Innov’Avenir organise une matinée à Suresnes et à Lille pour initier des enfants de 8 à 14 ans",
				LocalDateTime.now(), LocalDateTime.of(2019, 03, 19, 9, 15));
				
		projectRepository.save(CapSurLeCode);
		
		Event ffa = new Event(null, "Championnat de France", "Le championnat de l'année venez nombreux",
				LocalDateTime.now(), LocalDateTime.of(2019, 04, 19, 9, 15), 100, dakar, apps);
		ffa.setProject(CapSurLeCode);
		eventRepository.save(ffa);
	}
	
	

}

