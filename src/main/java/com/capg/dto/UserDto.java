package com.capg.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.capg.entities.City;
import com.capg.entities.EntityCap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;
	
	@NotBlank
	@Size(min=3, max= 30)
	private String name;
	
	@Size(max=50)
	private String lastName;
	
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	//@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
	
	private boolean isActive = true;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime lastUpdate;
	
	private City city;
	
	private EntityCap entityCap;

	private String role;
}
