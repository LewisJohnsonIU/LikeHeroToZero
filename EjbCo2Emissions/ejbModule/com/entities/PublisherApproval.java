package com.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Publishers")
public class PublisherApproval implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String user_name ; 
	private String password ;
	
	public PublisherApproval() {
	}
	
	
	
	public PublisherApproval( String username, String password) {
		this.user_name = username;
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
	
	

}
