package com.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@Size(min = 8, max = 15, message="password must be minimum of 8 characters and maximum of 15 characters")
	private String password;
	@NotEmpty
	private String about;
}
