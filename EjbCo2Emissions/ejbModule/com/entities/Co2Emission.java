package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="co2_emissions")
public class Co2Emission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String countryCode ; 
	private String year ; 
	private String country ;
	private BigDecimal co2EmissionKt ; 
	private boolean approuved = false ; 
	
	@ManyToOne
	private DataScientist dataScientist ; 
	
	public Co2Emission(String year, String country, BigDecimal co2data , String countryCode ,  boolean approuved) {
		this.year = year;
		this.country = country;
		this.approuved = approuved ; 
		this.co2EmissionKt = co2data ; 
		this.countryCode = countryCode ; 
	}
	
	public Co2Emission() {}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isApprouved() {
		return approuved;
	}

	public void setApprouved(boolean approuved) {
		this.approuved = approuved;
	} 
	
	
	public DataScientist getDataScientist() {
		return dataScientist;
	}
	
	public void setDataScientist(DataScientist dataScientist) {
		this.dataScientist= dataScientist ;
	}

	public BigDecimal getCo2EmissionKt() {
		return co2EmissionKt;
	}

	public void setCo2EmissionKt(BigDecimal co2EmissionKt) {
		this.co2EmissionKt = co2EmissionKt;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	


}
