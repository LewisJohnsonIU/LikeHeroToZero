package com.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "scientists")
public class DataScientist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	private String user_name ; 
	
	private String email ; 
	
	private String password ; 
	
	@OneToMany(mappedBy = "dataScientist")
    private List<Co2Emission> co2Emissions = new ArrayList<>();

	public DataScientist() {
	}
	
	
	public DataScientist(String username, String email, String password) {
		this.user_name = username;
		this.email = email ; 
		this.password = password;
	}



	public String getUsername() {
		return user_name;
	}

	public void setUsername(String username) {
		this.user_name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
