package com.dao;

import com.entities.Co2Emission;
import com.entities.DataScientist;

import java.util.List ; 

public interface ICo2Emissions {
	
	public void addCo2EmissonBatch(List<Co2Emission> co2Emissions) ; 
	public void addCo2Emisson(Co2Emission co2Emissions) ; 
	public List<Co2Emission> getCo2Emissions() ; 
	public List<Co2Emission> getApprouvedData() ; 
	public List<Co2Emission> getCo2EmissionsPerDs(DataScientist ds) ; 
	public List<Co2Emission> getCo2EmissionsByCountry(String country) ; 
	public List<Co2Emission> getCo2EmissionsByYear(String year) ; 
	public List<Co2Emission> getApprovedCo2EmissionsByCountry(String country) ; 
	public List<Co2Emission> getApprovedCo2EmissionsByYear(String year) ; 
	public List<String> getAllCountries() ; 
	public List<String> getYears() ; 
//	public List<String> getAllCountriesByDs(DataScientist ds) ; 
//	public List<String> getYearsByDs(DataScientist ds) ; 
	public Co2Emission getOne(Long id) ; 
	public void approuveCo2Emission(Long id) ;
	public void disApprouveCo2Emission(Long id) ;
	

}
