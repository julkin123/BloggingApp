package com.example.demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4, message = "name must have minimum 4 letters")
	private String name;
	@Email(message = "invalid email")

	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;

		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}

}
