package com.deploy.user.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Component
@Data
public class UserDto {

	@NotBlank(message = "User must have a valid name")
	String name;
	@NotBlank (message = "User Name cannot be null")
	String userName;
	@NotBlank(message = "passowrd cannot be null")
	String password;
	@NotBlank(message = "provide with a valid mail ID")
	String mail;
	
}
