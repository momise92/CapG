package com.capg;

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
	}
	

}

