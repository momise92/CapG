package com.capg.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.dao.UserAppRepository;
import com.capg.entities.UserApp;


@Component
public class Blacklist {

	@Autowired
	private UserAppRepository userAppRepository;

	private Set<String> disabled = new HashSet<>();

	public void loadDisabledFromDB()
	{
		for(UserApp u : userAppRepository.findByIsActiveFalseOrderByNameAsc())
		{
			disabled.add(u.getName());
			
		}
	}
	
	public boolean userInList(String user)
	{
		return disabled.contains(user);
	}
	
	public void addUser(String user)
	{
		disabled.add(user);
	}
	
	public void removeUser(String user)
	{
		disabled.remove(user);
	}
	
	public void CleanBlackList()
	{
		disabled.clear();
	}

	public List<String> getDisabled() {
		return new ArrayList<String>(disabled);
	}

}
