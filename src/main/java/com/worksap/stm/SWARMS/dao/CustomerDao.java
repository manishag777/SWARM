package com.worksap.stm.SWARMS.dao;

import java.io.IOException;




import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.utils.Utilities;



@Repository
public class CustomerDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT_CUSTOMER = "INSERT INTO customer "
			+ " (firstname, lastname, email, phoneno, dob, gender, pincode, city, state, country, referrerId)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String FETCH_BY_ID = "SELECT * FROM customer where id = ?";
	private static final String FETCH_SPORTS_BY_ID = "SELECT * FROM customer_sport where cust_id = ?";
	private static final String FETCH_ID =  "SELECT LAST_INSERT_ID() as id" ;
	private static final String INSERT_CUSTOMER_SPORT = "INSERT INTO customer_sport(cust_id, sport_id) VALUES (?, ?)";
	
	private static final String UPDATE_CUSTOMER =  "UPDATE CUSTOMER SET firstname = ?, "
	+" lastname = ?, email = ?, phoneno = ?, gender = ?, pincode = ?, city = ?, state = ?, country = ?"
	+ "where id = ?";
	
	private static final String UPDATE_GIFTCARD =  "UPDATE CUSTOMER SET giftcard_id = ? "
			+ "where id = ?";
	
	private static final String UPDATE_EMAIL =  "UPDATE CUSTOMER SET email = ? where id = ?";
	
	private static final String UPDATE_ISNEWCUSTOMER =  "UPDATE CUSTOMER SET isNewCustomer = ? where id = ?";

	
	public int insert(CustomerDto customer) throws IOException {
		try {
				template.update(INSERT_CUSTOMER, (ps) -> {
				ps.setString(1, customer.getFirstName());
				ps.setString(2, customer.getLastName());
				ps.setString(3, Utilities.formatString(customer.getEmailId()));
				ps.setString(4, Utilities.formatString(customer.getPhoneNo()));
				ps.setDate(5, Utilities.formatDate(customer.getDOB()));
				ps.setInt(6, customer.getGender());
				ps.setInt(7, customer.getPinCode());
				ps.setString(8, Utilities.formatString(customer.getCity()));
				ps.setString(9, Utilities.formatString(customer.getState()));
				ps.setString(10, Utilities.formatString(customer.getCountry()));
				if(customer.getReferrerId()!=0)
					ps.setInt(11, customer.getReferrerId());
				else 
					ps.setObject(11, null);
				});
				
	 
			 int id = template.queryForObject(
					 FETCH_ID, Integer.class);
			 System.out.println("id = " + id);
			 
			 template.batchUpdate(INSERT_CUSTOMER_SPORT, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, id);
						ps.setString(2, customer.getSportsInterest().get(i));
					}

					@Override
					public int getBatchSize() {
						return customer.getSportsInterest().size();
					}
				});
			 
			 return id;
				
		} catch (DataAccessException e) {
			
			System.out.println("At CustomerDao :" +e);
			throw new IOException(e);
		}
	}
	
	public CustomerDto getCustomerById(int id){
		
		CustomerDto customerDto = new CustomerDto();

		 template.queryForObject(
				FETCH_BY_ID,
				(rs, rownum) -> {
					customerDto.setFirstName(rs.getString("firstname"));
					customerDto.setLastName(rs.getString("lastname"));
					customerDto.setCity(rs.getString("city"));
					customerDto.setCountry(rs.getString("country"));
					customerDto.setState(rs.getString("state"));
					customerDto.setPinCode(rs.getInt("pincode"));
					customerDto.setPhoneNo(rs.getString("phoneno"));
					customerDto.setGender(rs.getInt("gender"));
					customerDto.setEmailId(rs.getString("email"));
					customerDto.setDOB(rs.getString("dob"));
					customerDto.setReferrerId(rs.getInt("referrerId"));
					return customerDto;
				},id);
		
		
		
		List<String> sportInterest = new ArrayList<String>();
		template.query(
				FETCH_SPORTS_BY_ID,
				(rs,rownum)->{
					String si =   rs.getString("sport_id");
					sportInterest.add(si);
					return si;
				},id);
		
		customerDto.setSportsInterest(sportInterest);
		return customerDto;
	}
	
	public void update(CustomerDto customer) throws IOException {
		
		
		
			try {
					template.update(UPDATE_CUSTOMER, (ps) -> {
					ps.setString(1, customer.getFirstName());
					ps.setString(2, customer.getLastName());
					ps.setString(3, Utilities.formatString(customer.getEmailId()));
					ps.setString(4, Utilities.formatString(customer.getPhoneNo()));
					ps.setInt(5, customer.getGender());
					ps.setInt(6, customer.getPinCode());
					ps.setString(7, customer.getCity());
					ps.setString(8, customer.getState());
					ps.setString(9, customer.getCountry());
					ps.setString(10, customer.getId());
				});
			} catch (DataAccessException e) {
				
				System.out.println("At EmployeeDao :" +e);
				throw new IOException(e);
			}
	
	}

	public void updateGiftCardId(int custId, int giftCardId) throws IOException {
		// TODO Auto-generated method stub
		
		try {
			template.update(UPDATE_GIFTCARD, (ps) -> {
			ps.setInt(2, custId);
			ps.setInt(1,giftCardId);
			
		});
	} catch (DataAccessException e) {
		
		System.out.println("At CustomerDao :" +e);
		throw new IOException(e);
	}
		
	}

	public void updateEmailId(int custId, String emailId) throws IOException {
		
		try {
			//System.out.println("Updating emaild Id :" +e);
			template.update(UPDATE_EMAIL, (ps) -> {
			ps.setInt(2, custId );
			ps.setString(1, emailId);
			});
		}
	    catch(Exception e){
	    	System.out.println("At CustomerDao :" +e);
			throw new IOException(e);
	    }
	}

	public void updateCustomerIsNotNew(int custId) throws IOException {
		try {
			//System.out.println("Updating emaild Id :" +e);
			template.update(UPDATE_ISNEWCUSTOMER, (ps) -> {
			ps.setInt(2, custId );
			ps.setInt(1, 1);
			});
		}
	    catch(Exception e){
	    	System.out.println("At CustomerDao :" +e);
			throw new IOException(e);
	    }
	}
}