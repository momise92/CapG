package com.capg.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDto {
	
	@Size(min = 8)
	private String currentPassword;
	
	@Size(min = 8)
    private String newPassword;

}
