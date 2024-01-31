package com.metier;

import javax.sound.midi.Soundbank;

import com.dao.IDataScientist;
import com.entities.Co2Emission;
import com.entities.DataScientist;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class DataScientistImpl implements IDataScientist {
	
	@PersistenceContext(unitName = "UP_CO2DATA" )
	private EntityManager em ; 

	@Override
	public DataScientist login(String username, String password) {
		
		try {
			TypedQuery<DataScientist> query = em.createQuery("SELECT d FROM DataScientist d WHERE d.user_name = :username And d.password = :password", DataScientist.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
			System.out.println(query.getSingleResult());;
			return query.getSingleResult() ; 
		}catch(Exception e) {
			System.out.println(e);
			return null ; 
		}
		
		
	}

	@Override
	public DataScientist getOne(Long id) {
		DataScientist ds = em.find(DataScientist.class, id) ; 
		if(ds == null) throw new RuntimeException("Data not found")  ; 
		return ds ; 
	}

}
