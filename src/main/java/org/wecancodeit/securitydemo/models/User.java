package org.wecancodeit.securitydemo.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	@JsonIgnore
	private String password;

	private String role;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}

	protected User() {
	} // JPA

	public User(String username, String password, String role) {
		this.username = username;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.role = role;
	}



}
