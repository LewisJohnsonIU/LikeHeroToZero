package com.metier;

import com.dao.IPublisher;
import com.entities.DataScientist;
import com.entities.PublisherApproval;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless
public class PublishersImpl implements IPublisher {
	
	
	@PersistenceContext(unitName = "UP_CO2DATA" )
	private EntityManager em ; 

	@Override
	public boolean login(String username, String password) {
		
		try {
			TypedQuery<PublisherApproval> query = em.createQuery("SELECT p FROM PublisherApproval p WHERE p.user_name = :username And p.password = :password", PublisherApproval.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
			System.out.println(query.getSingleResult());;
			return true ; 
			
		}catch(Exception e) {
			System.out.println(e);
			return false ; 
		}
		
		
	}

}
