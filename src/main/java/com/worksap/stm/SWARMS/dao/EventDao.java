package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;










import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.EventDto;
import com.worksap.stm.SWARMS.entity.TopProductsEntity;
import com.worksap.stm.SWARMS.entity.TopSeperateProductEntity;
import com.worksap.stm.SWARMS.utils.Utilities;










import org.springframework.jdbc.core.BatchPreparedStatementSetter;

@Repository
public class EventDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_ID =  "SELECT LAST_INSERT_ID() as id" ;
	private static final String INSERT_EVENT= "INSERT INTO new_events"
			+ " (name, address, detail, fromDate, toDate, pinCode, particpantCount, expectedRevenue, expectedCustomerVisit, sportsType)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String INSERT_EVENT_SPORTS = "insert into event_sport (event_id, sport_id) values(?,?)";
	private static final String INSERT_EVENT_STORES = "insert into event_store (event_id, store_id) values(?,?)";
//	private static final String FETCH_EVENT_LISTS = "select se.* from sportevent se inner join event_sport es on se.id = es.event_id inner join"
//			+ " event_store est on se.id = est.event_id where es.sport_id = ? and est.store_id = ? and ((fromDate between ? and ?) or (toDate between ? and ?)) order by fromDate DESC ";
	private static final String FETCH_EVENT_LISTS = "select * from new_events where sportsType = ? and ((fromDate between ? and ?) or (toDate between ? and ?)) order by fromDate DESC ";

	public List<EventDto> fetchEventDto(String sport, String fromDate, String toDate){
		
		return template.query(FETCH_EVENT_LISTS, new Object[]{sport,fromDate,toDate,fromDate,toDate}, (rs,column)->{
			EventDto eventDto = new EventDto();
			eventDto.setEventName(rs.getString("name"));
			eventDto.setEventDetail(rs.getString("detail"));
			eventDto.setFromDate(rs.getString("fromDate"));
			eventDto.setToDate(rs.getString("toDate"));
			eventDto.setId(rs.getInt("id"));
			eventDto.setAddress(rs.getString("address"));
			eventDto.setPinCode(rs.getInt("pincode"));
			eventDto.setParticipantCount(rs.getInt("particpantCount"));
			eventDto.setRevenueGenerated(rs.getInt("revenueGenerated"));
			eventDto.setCustomerVisited(rs.getInt("customerVisited"));
			eventDto.setExpectedRevenue(rs.getInt("expectedRevenue"));
			eventDto.setExpectedCustomerVisit(rs.getInt("expectedCustomerVisit"));
			eventDto.setTargetedRevenue(rs.getInt("targetedRevenue"));
			eventDto.setTargetedCustomer(rs.getInt("targetedCustomer"));
			eventDto.setTask1(rs.getInt("task1"));
			eventDto.setTask2(rs.getInt("task2"));
			eventDto.setTask3(rs.getInt("task3"));
			eventDto.setSportType(rs.getString("sportsType"));
			return eventDto;
		});
		
	}; 
	
	public int insert(EventDto eventDto) throws IOException {
		try {
				template.update(INSERT_EVENT, (ps) -> {
				ps.setString(1, eventDto.getEventName());
				ps.setString(2, eventDto.getAddress());
				ps.setString(3, eventDto.getEventDetail());
				String dateArr[] = eventDto.getEventDate().split("-");
				ps.setDate(4, Utilities.formatDate(dateArr[0].trim()));
				ps.setDate(5, Utilities.formatDate(dateArr[1].trim()));
				ps.setInt(6,eventDto.getPinCode());
				ps.setInt(7, eventDto.getParticipantCount());
				ps.setInt(8, eventDto.getExpectedRevenue());
				ps.setInt(9, eventDto.getExpectedCustomerVisit());
				ps.setString(10,eventDto.getSportType());
				
			});
				
				int id = template.queryForObject(
						 FETCH_ID, Integer.class);
				 //updateSport(eventDto, id);
				 //updateStore(eventDto, id);
			 
			 return id;
				
		} catch (DataAccessException e) {
			
			System.out.println("At CustomerDao :" +e);
			throw new IOException(e);
		}
	}
	
	public void updateSport(EventDto eventDto, int id){
		
		 template.batchUpdate(INSERT_EVENT_SPORTS, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setInt(1, id);
					ps.setString(2, eventDto.getSportId().get(i));
				}

				@Override
				public int getBatchSize() {
					return eventDto.getSportId().size();
				}

				
			});
		
	}
	
	public void updateStore(EventDto eventDto, int id){
		
		 template.batchUpdate(INSERT_EVENT_STORES, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setInt(1, id);
					ps.setString(2, eventDto.getStoreId().get(i));
				}

				@Override
				public int getBatchSize() {
					return eventDto.getStoreId().size();
				}

				
			});
		
	}

	public List<TopProductsEntity> getTopProductsData(int eventId) {
		// TODO Auto-generated method stub
		TopSeperateProductEntity topSeperateProductEntity = new TopSeperateProductEntity(3, "XAZER","Football", 100, 10,50);
		List<TopSeperateProductEntity> listEntity = new ArrayList<>();
		for(int i=0; i<10; i++){
			listEntity.add(topSeperateProductEntity);
		}
		TopProductsEntity topProductsEntity =  new TopProductsEntity(20, 10000, 10, 20, listEntity);
		List<TopProductsEntity> res = new ArrayList<>();
		for(int i=0; i<10; i++){
			res.add(topProductsEntity);
		}
		return res;
	}
	
	public void saveCustomerTarget(int id, int target){
		 String query = "update new_events set targetedCustomer = ?, task2 = 1 where id = ? ";
		
		template.update(query, ps->{
			ps.setInt(1, target);
			ps.setInt(2,id);
		});
		
	}
	
	public void saveRevenueTarget(int id, int target){
		 String query = "update new_events set targetedRevenue = ?, task1 = 1 where id = ? ";	
		template.update(query, ps->{
			ps.setInt(1, target);
			ps.setInt(2,id);
		});
		
	}
	
	public void saveMailTask(int id){
		 String query = "update new_events set task3 = 1 where id = ? ";	
		template.update(query, ps->{
			ps.setInt(1,id);
		});
		
	}

	
}
