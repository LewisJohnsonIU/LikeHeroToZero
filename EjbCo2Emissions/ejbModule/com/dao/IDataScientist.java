package com.dao;

import com.entities.DataScientist;

public interface IDataScientist {
	
	public DataScientist login(String username, String password) ;

	public DataScientist getOne(Long id); 
 
}
