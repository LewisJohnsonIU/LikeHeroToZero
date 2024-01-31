package com.metier;

import java.util.List;

import com.dao.ICo2Emissions;
import com.entities.Co2Emission;
import com.entities.DataScientist;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


@Stateless
public class Co2EmissionsImp implements ICo2Emissions {

	@PersistenceContext(unitName = "UP_CO2DATA" )
	private EntityManager em ; 
	
	
	@Override
	public void addCo2Emisson(Co2Emission co2Emission) {
		em.persist(co2Emission);
	}
	
	

	@Override
	public List<Co2Emission> getCo2Emissions() {
		Query req = em.createQuery("Select c from Co2Emission c ") ; 
		List<Co2Emission> list = null ; 
		list = req.getResultList();
		return list ; 

	}

	@Override
	public Co2Emission getOne(Long id) {
		Co2Emission co2Emission = em.find(Co2Emission.class, id) ; 
		if(co2Emission == null) throw new RuntimeException("Data not found") ; 
		return co2Emission ;
	}

	@Override
	public void approuveCo2Emission(Long id) {
		Co2Emission co2Emission =  getOne(id) ; 
		co2Emission.setApprouved(true);
		em.persist(co2Emission);  // not obligated it made automatically
	}
	
	@Override
	public void disApprouveCo2Emission(Long id) {
		Co2Emission co2Emission =  getOne(id) ; 
		co2Emission.setApprouved(false);
		em.persist(co2Emission);  // not obligated it made automatically
	}

	@Override
	public List<Co2Emission> getCo2EmissionsPerDs(DataScientist ds) {
		TypedQuery<Co2Emission> query = em.createQuery("SELECT ce FROM Co2Emission ce WHERE ce.dataScientist = :dataScientist", Co2Emission.class);
        query.setParameter("dataScientist", ds);

        return query.getResultList();
	}
	
	@Override
	public List<Co2Emission> getApprouvedData() {
		TypedQuery<Co2Emission> query = em.createQuery("SELECT c FROM Co2Emission c WHERE c.approuved = :approuved", Co2Emission.class);
        query.setParameter("approuved", true);
        return query.getResultList();
	}

	@Override
	public List<Co2Emission> getCo2EmissionsByCountry(String country) {
		TypedQuery<Co2Emission> query = em.createQuery("SELECT ce FROM Co2Emission ce WHERE ce.country = :country", Co2Emission.class);
        query.setParameter("country", country);
        return query.getResultList();
	}
	
	@Override
	public List<Co2Emission> getCo2EmissionsByYear(String year) {
		TypedQuery<Co2Emission> query = em.createQuery("SELECT ce FROM Co2Emission ce WHERE ce.year = :year", Co2Emission.class);
        query.setParameter("year", year);
   
        return query.getResultList();
	}


	@Override
	public List<String> getAllCountries() {
		  Query query = em.createQuery("SELECT DISTINCT c.country FROM Co2Emission c");
		    return query.getResultList();
	}
	
	@Override
	public List<String> getYears() { 
		  Query query = em.createQuery("SELECT DISTINCT c.year FROM Co2Emission c");
		    return query.getResultList();
	}



	@Override
	public void addCo2EmissonBatch(List<Co2Emission> co2Emissions) {
		 for (Co2Emission co2Emission : co2Emissions) {
		        em.persist(co2Emission);
		    }
	}



	@Override
	public List<Co2Emission> getApprovedCo2EmissionsByCountry(String country) {
		TypedQuery<Co2Emission> query = em.createQuery("SELECT ce FROM Co2Emission ce Where ce.country = :country and ce.approuved = :approuved", Co2Emission.class);
        query.setParameter("country", country);
        query.setParameter("approuved", true);
        return query.getResultList();
	}



	@Override
	public List<Co2Emission> getApprovedCo2EmissionsByYear(String year) {
		TypedQuery<Co2Emission> query = em.createQuery("SELECT ce FROM Co2Emission ce Where ce.year = :year and ce.approuved = :approuved", Co2Emission.class);
        query.setParameter("year", year);
        query.setParameter("approuved", true);
        return query.getResultList();
	}



//	@Override
//	public List<String> getAllCountriesByDs(DataScientist ds) {
//		TypedQuery<Co2Emission> query = em.createQuery("SELECT DISTINCT c.country FROM Co2Emission c where ce.dataScientist = :dataScientist", Co2Emission.class);
//        query.setParameter("dataScientist", ds);
//        return query.getResultList();
//        
//	
//	}

//
//
//	@Override
//	public List<String> getYearsByDs(DataScientist ds) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
