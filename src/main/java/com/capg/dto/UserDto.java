package com.capg.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(min=8)
	private String password;
	
	@Size(min=8)
	private String checkPassword;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
	
	private boolean isActive = true;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime lastUpdate;
	
	private String city;
	
	private String entityCap;

	private String status;
}
