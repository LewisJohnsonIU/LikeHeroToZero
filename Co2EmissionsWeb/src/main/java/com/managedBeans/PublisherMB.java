package com.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import com.dao.IPublisher;
import com.entities.PublisherApproval;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("publisherMB")
@SessionScoped
public class PublisherMB implements Serializable {

	
 
		private static final long serialVersionUID = 1L;
		@EJB
		private IPublisher metier ; 
		
		private String loginError ; 
		
		PublisherApproval publisher = new PublisherApproval() ; 
		
		public String login() {
			if(metier.login(publisher.getUsername(), publisher.getPassword())) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true) ; 
		    	session.setAttribute("loggedInUser", publisher);
				return "approuv?faces-redirect=true" ; 

			}else {
				loginError = "Please check your username and password" ; 
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
			        externalContext.redirect("http://localhost:8080/Co2EmissionsWeb/faces/DataPublisher/login.xhtml"); // Redirect to the login page
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		}
		
		
		public void checkLoggedInUser() {
		    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(false);
		    
		    if ((session == null || session.getAttribute("loggedInUser") == null) && !externalContext.getRequestServletPath().contains("/DataPublisher/login.xhtml")) {
		        try {
		        	session.invalidate() ; 
		            externalContext.redirect("login.xhtml");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		public PublisherApproval getPublisher() {
			return publisher;
		}



		public String getLoginError() {
			return loginError;
		}
		


	

}
