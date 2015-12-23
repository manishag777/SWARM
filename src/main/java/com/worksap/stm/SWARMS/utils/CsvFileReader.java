package com.worksap.stm.SWARMS.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dao.CustomerDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;



@Repository
public class CsvFileReader {
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		
		@Autowired
		CustomerDao customerDao;
		
		
		public  void readCsvFile(String fileName) {

			BufferedReader fileReader = null;
	     
	        try {
	        	
	        	//Create a new list of student to be filled by CSV file data 
	        	List students = new ArrayList();
	        	
	            String line = "";
	            
	            //Create the file reader
	            fileReader = new BufferedReader(new FileReader(fileName));
	            
	            //Read the CSV file header to skip it
	            fileReader.readLine();
	            
	            //Read the file line by line starting from the second line
	            int count=0;
	            int id = 48;
	            while ((line = fileReader.readLine()) != null) {
	                //Get all tokens available in line
	            	count++;
	            	if(count==1) continue;
	            	
	                String[] tokens = line.split(COMMA_DELIMITER);
	                
	                if (tokens.length > 0) {
	                	//Create a new student object and fill his  data
	                	CustomerDto customer = new CustomerDto();
	                	customer.setFirstName(tokens[0]);
	                	customer.setLastName(tokens[1]);
	                	customer.setEmailId(tokens[2]);
	                	customer.setPhoneNo(tokens[3]);
	                	customer.setDOB(tokens[4]);
	                	customer.setGender(Integer.parseInt(tokens[5]));
	                	customer.setPlace(tokens[6]);
	                	customer.setCity(tokens[7]);
	                	customer.setState(tokens[8]);
	                	customer.setCountry(tokens[9]);
	                	customer.setPinCode(Integer.parseInt(tokens[10]));
	                	customer.setLng(Double.parseDouble(tokens[11]));
	                	customer.setLat(Double.parseDouble(tokens[12]));
	                	id++;
	                	System.out.println("id = "+ id + "place = "+ customer.getPlace());
	                	customerDao.insertPlace(customer.getPlace(),id);
					}
	            }
	            
	            //Print the new student list
	            /*for (Student student : students) {
					System.out.println(student.toString());
				}*/
	        } 
	        catch (Exception e) {
	        	System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	            	System.out.println("Error while closing fileReader !!!");
	                e.printStackTrace();
	            }
	        }

		}
}
