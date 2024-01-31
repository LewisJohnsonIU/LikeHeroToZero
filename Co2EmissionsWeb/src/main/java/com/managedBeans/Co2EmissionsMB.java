package com.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.dao.ICo2Emissions;
import com.dao.IDataScientist;
import com.entities.Co2Emission;
import com.entities.DataScientist;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("co2MB")
@SessionScoped
public class Co2EmissionsMB implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@EJB
	private ICo2Emissions metier ; 
	
	@EJB
	private IDataScientist metierDs ; 
	
	private Co2Emission co2Emission = new Co2Emission() ; 
	private String country ; 
	private String year ; 
	private List<Co2Emission> listCo2Emissions  ;
	private List<String> listCountries ; 
	private List<Co2Emission> listApprouvedCo2Emissions ;
	private List<Co2Emission> listCo2EmissionsPerScientist ;
	private int totalData ; 
	
	public void initDb() { 
	 if(metier.getCo2Emissions().isEmpty()) {
		   Co2DataSet co2DataSet = new Co2DataSet();
		    List<Co2Emission> co2Emissions = co2DataSet.getCo2DataSet();

		    int batchSize = 100;
		    for (int i = 0; i < co2Emissions.size()/2; i += batchSize) {
		        int endIndex = Math.min(i + batchSize, co2Emissions.size());
		        List<Co2Emission> batch = co2Emissions.subList(i, endIndex);
		        metier.addCo2EmissonBatch(batch);
		    }
			
	 }else {
		 System.out.println("Already initialized");
	 }
	}
	
	public String saveCo2EmissionData() {
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		DataScientist loggedInUser = (DataScientist) externalContext.getSessionMap().get("loggedInUser");
		System.out.println(loggedInUser.getId());
		co2Emission.setDataScientist(metierDs.getOne(loggedInUser.getId())); 
		metier.addCo2Emisson(co2Emission);
		return "co2Emissions?success=Co2 emission added successfully" ; 
		    

	} 
	
	public String back() {
		return "co2Emissions?faces-redirect=true" ; 
	} 
	
	public Co2Emission getCo2Emission() {
		return co2Emission;
	}
	
	public void setCo2Emission(Co2Emission co2Emission) {
		this.co2Emission = co2Emission;
	}
	
	
	
	
	
	public List<Co2Emission> getListCo2Emissions() { 
		if(this.country != null) {
			listCo2Emissions = metier.getCo2EmissionsByCountry(this.country) ;

		}
		if(this.year != null) {
			listCo2Emissions.addAll(metier.getCo2EmissionsByYear(this.year)) ; 
		}
		if(this.country == null && this.year == null) {
			listCo2Emissions = metier.getCo2Emissions() ;
		}
		setTotalData(listCo2Emissions.size());
		return listCo2Emissions;
	}
	
	public void setListCo2Emissions(List<Co2Emission> listCo2Emissions) { 
		this.listCo2Emissions = listCo2Emissions ; 

	}
	
	
	public List<Co2Emission> getListApprouvedCo2Emissions() { 
		listApprouvedCo2Emissions = metier.getApprouvedData() ; 
		if(this.country != null) {
			listApprouvedCo2Emissions = metier.getApprovedCo2EmissionsByCountry(this.country) ;

		}
		if(this.year != null) {
			listApprouvedCo2Emissions = metier.getCo2EmissionsByYear(this.year) ; 
		}
		if(this.country == null && this.year == null) {
			listApprouvedCo2Emissions = metier.getApprouvedData() ;
		}
		setTotalData(listCo2Emissions.size());
		country = null ; 
		year = null ; 
		return listApprouvedCo2Emissions ; 
	}
	
	
	public int getTotalData() {
		return totalData ;  
	}
	
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	
	public List<String> getListCountries(){ 
		listCountries =  metier.getAllCountries() ; 
		return listCountries ; 
	}
	
	public void setListCountries(List<String> listCountries){ 
		this.listCountries = listCountries  ; 
	}
	
	public List<String> getYears(){ 
		return metier.getYears() ; 
	}

	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public boolean filterDataByCountry() {
		System.out.println("filter data by"+this.country);
		listCo2Emissions = metier.getCo2EmissionsByCountry(this.country) ;  
		return true ; 

		
	}
	
	public boolean filterDataByYear() {
		System.out.println("filter data by"+this.year);
		listCo2Emissions = metier.getCo2EmissionsByYear(this.year) ;  
		return true ;

		
	}
	
	public void approuve(Long id) {
		metier.approuveCo2Emission(id);
	}
	
	public void disApprouve(Long id) {
		metier.disApprouveCo2Emission(id) ; 
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Co2Emission> getListCo2EmissionsPerScientist() {
		List<String> countries = new ArrayList<>() ;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		DataScientist ds = (DataScientist) externalContext.getSessionMap().get("loggedInUser");
		listCo2EmissionsPerScientist=  metier.getCo2EmissionsPerDs(ds) ; 
		setTotalData(listCo2EmissionsPerScientist.size());
		for(int i=0; i<listCo2EmissionsPerScientist.size();i++) {
			countries.add(listCo2EmissionsPerScientist.get(i).getCountry()) ; 
		}
		setListCountries(countries);
		return listCo2EmissionsPerScientist;
		
	}

	public void setListCo2EmissionsPerScientist(List<Co2Emission> listCo2EmissionsPerScientist) {
		this.listCo2EmissionsPerScientist = listCo2EmissionsPerScientist;
	}
	
}
