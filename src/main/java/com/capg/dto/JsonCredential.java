package com.capg.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JsonCredential {
	
	@Email
	@NotBlank
	private String email;
	
	@Size(min=8, max=30)
	@NotBlank
	private String password;

}
