package com.capg.dto;

import com.capg.entities.City;
import com.capg.entities.EntityCap;
import com.capg.entities.Event;
import com.capg.entities.RoleApp;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class UpdateUser {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String phoneNumber;

    private boolean isActive = true;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdate;

    private City city;

    private EntityCap entityCap;

    private String role;

    private RoleApp status;

    private Set<Event> eventsSubscribe = new HashSet<>();

    private Set<Event> createdEvents = new HashSet<>();

}
