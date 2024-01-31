package com.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;   
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entities.Co2Emission;
import com.microsoft.schemas.office.visio.x2012.main.CellType;


public class Co2DataSet {
	
	private List<Co2Emission> co2DataSet = new ArrayList<Co2Emission>() ; 
	
	public Co2DataSet() { 
		
	}
	
	public List<Co2Emission> getCo2DataSet() { 
		 List<String> years = new ArrayList<>();
	        try {
	            FileInputStream file = new FileInputStream(new File("C:/Users/dibes/Downloads/co2_emissions_kt.xlsx"));
	            Workbook workbook = new XSSFWorkbook(file);
	            Sheet sheet = workbook.getSheetAt(0);
	            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
	                Row row = sheet.getRow(i);
	                String countryCode = row.getCell(0).toString() ;
	                String countryName = row.getCell(1).toString();
	                String year = String.valueOf((int)(row.getCell(2).getNumericCellValue())) ; 
	                BigDecimal co2Value = new BigDecimal(row.getCell(3).toString()) ; 
	                 co2DataSet.add(new Co2Emission(year,countryName,co2Value, countryCode , true)) ; 
	                 
	                
	            }
	            
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	        return co2DataSet ; 
	        
	}
	
	

}
