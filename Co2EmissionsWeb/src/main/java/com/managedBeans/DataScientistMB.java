package com.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import jakarta.faces.context.ExternalContext;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import com.dao.IDataScientist;
import com.entities.DataScientist;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("dataScientistMB")
@RequestScoped
public class DataScientistMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private IDataScientist metier ; 
	
	private String loginError ; 
	
	
	public static  HttpSession loggedIN = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true) ; 
	

	
	DataScientist dataScientist = new DataScientist() ; 
	
	public String login() {
		this.dataScientist = metier.login(dataScientist.getUsername(), dataScientist.getPassword()) ; 
		if( this.dataScientist != null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true) ; 
	    	session.setAttribute("loggedInUser", dataScientist);
			return "co2Emissions?faces-redirect=true" ; 

		}else {
			return "login?loginError=Please check your username and password&faces-redirect=true" ; 
		}
		
	}
	
	public void logout() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(false);
		    if (session != null) {
		        session.invalidate(); // Destroy the session

		    }
		    try {
		        externalContext.redirect("http://localhost:8080/Co2EmissionsWeb/faces/DataScientist/login.xhtml"); // Redirect to the login page
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	
	public void checkLoggedInUser() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    HttpSession session = (HttpSession) externalContext.getSession(false);
	    
	    if ((session == null || session.getAttribute("loggedInUser") == null) && !externalContext.getRequestServletPath().contains("/DataScientist/login.xhtml")) {
	        try {
	        	session.invalidate() ; 
	            externalContext.redirect("login.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public DataScientist getDataScientist() {
		return dataScientist;
	}

	public String getLoginError() {
		return loginError;
	}
	


}
