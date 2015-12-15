package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.EventDto;
import com.worksap.stm.SWARMS.dto.StallEventDto;
import com.worksap.stm.SWARMS.dto.UserEventDto;
import com.worksap.stm.SWARMS.utils.Utilities;


@Repository
public class StallEventDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT_EVENT= "INSERT INTO stallEvent"
			+ " (eventName, fromDate, toDate, sport, placeEvent, coName, coPhone, coEmail, detail)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String FETCH_ID =  "SELECT LAST_INSERT_ID() as id" ;
	
	private static final String FETCH_EVENT_LISTS = "select * from stallEvent where ((fromDate between ? and ?) or (toDate between ? and ?)) order by fromDate DESC ";

	
	
	public List<StallEventDto> fetchStallEventDto(String sport, String fromDate, String toDate){
		
		return template.query(FETCH_EVENT_LISTS, new Object[]{fromDate,toDate,fromDate,toDate}, (rs,column)->{
			
			StallEventDto stallEventDto = new StallEventDto();
			stallEventDto.setId(rs.getInt("id"));
			stallEventDto.setEventName(rs.getString("eventName"));
			stallEventDto.setPlaceEvent(rs.getString("placeEvent"));
			stallEventDto.setDetail(rs.getString("detail"));
			stallEventDto.setFromDate(rs.getString("fromDate"));
			stallEventDto.setToDate(rs.getString("toDate"));
			stallEventDto.setCoName(rs.getString("coName"));
			stallEventDto.setCoEmail(rs.getString("coEmail"));
			stallEventDto.setCoPhone(rs.getString("cophone"));
			stallEventDto.setCmStatus(rs.getInt("cmStatus"));
			stallEventDto.setCmTime(rs.getString("cmTime"));
			stallEventDto.setReason(rs.getString("reason"));
			stallEventDto.setSportType(rs.getString("sport"));
			stallEventDto.setMStatus(rs.getInt("mstatus"));
			stallEventDto.setStallNo(rs.getInt("stallNo"));
			stallEventDto.setTrainingTime(rs.getString("trainingTime"));
			stallEventDto.setDiscount(rs.getInt("discount"));
			stallEventDto.setEventStatus(rs.getInt("eventStatus"));
			stallEventDto.setRCustomer(rs.getInt("rCustomer"));
			stallEventDto.setProfit(rs.getInt("profit"));
			stallEventDto.setRevenue(rs.getInt("revenue"));
			stallEventDto.setACustomer(rs.getInt("aCustomer"));
			stallEventDto.setTaskStatus(rs.getInt("taskStatus"));
			stallEventDto.setFees(rs.getInt("fees"));
			stallEventDto.setParticipantCount(rs.getInt("particpantCount"));
			return stallEventDto;
		});
		
	}; 
	
	public List<String> fetchUsersList(int eventId){
		String query = "select username from usereventtask where eventid = ?" ;
		List<String> userList =  template.query(query, new Object[]{eventId}, (rs,column)->{
			return rs.getString("username");
		});
		
		return userList;
		
	}

	

	public int insert(StallEventDto eventDto) throws IOException {
		try {
				template.update(INSERT_EVENT, (ps) -> {
				ps.setString(1, eventDto.getEventName());
				String dateArr[] = eventDto.getEventDate().split("-");
				ps.setDate(2, Utilities.formatDate(dateArr[0].trim()));
				ps.setDate(3, Utilities.formatDate(dateArr[1].trim()));
				
				ps.setString(4, eventDto.getSportType());
				ps.setString(5, eventDto.getPlaceEvent());
				
				
				ps.setString(6,eventDto.getCoName());
				ps.setString(7, eventDto.getCoPhone());
				ps.setString(8, eventDto.getCoEmail());
				ps.setString(9, eventDto.getDetail());
			});
				
				int id = template.queryForObject(
						 FETCH_ID, Integer.class);
				 //updateSport(eventDto, id);
				 //updateStore(eventDto, id);
			 
			 return id;
				
		} catch (DataAccessException e) {
			
			System.out.println("At StallEventDao :" +e);
			throw new IOException(e);
		}
	}



	public void updateCmStatus(int eventId, int value) {
		// TODO Auto-generated method stub
		String query = "update stallEvent set cmStatus = ? where id = ? ";	
		template.update(query, ps->{
			ps.setInt(1,value);
			ps.setInt(2,eventId);
		});
		
	}



	public void upDateMeetingTime(int eventId, String dateTime) {
		// TODO Auto-generated method stub
		
		String query = "update stallEvent set cmTime = ? where id = ? ";	
		template.update(query, ps->{
			ps.setString(1,dateTime);
			ps.setInt(2,eventId);
		});
		
	}



	public void updateMStatus(int eventId, int value) {
		
		String query = "update stallEvent set mStatus = ? where id = ? ";	
		template.update(query, ps->{
			ps.setInt(1,value);
			ps.setInt(2,eventId);
		});
		
	}



	public void saveFeesAndStallCount(int eventId, int stallCount,
			int fees) {
		// TODO Auto-generated method stub
		
		String query = "update stallEvent set stallNo = ?, fees = ? where id = ? ";	
		template.update(query, ps->{
			ps.setInt(1,stallCount);
			ps.setInt(2,fees);
			ps.setInt(3,eventId);
		});
		
	}



	public void saveAssignedUser(UserEventDto userEventDto) {
		// TODO Auto-generated method stub
		String query = "update stallEvent set trainingTime = ?, taskStatus = 1 where id = ?";
		template.update(query, ps->{
			ps.setString(1,userEventDto.getTrainingTime());
			ps.setInt(2,userEventDto.getEventId());
		});
		
		String deletequery = "delete from userEventTask where eventid = ?";
		
		template.update(deletequery, (ps)->{
			ps.setInt(1, userEventDto.getEventId());
		});
		
		query = "INSERT INTO userEventTask (username, eventid)"
			+ " VALUES (? , ?)";
		
			template.batchUpdate(query, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setString(1, userEventDto.getUser().get(i));
					ps.setInt(2, userEventDto.getEventId());
				}

				@Override
				public int getBatchSize() {
					return userEventDto.getUser().size();
				}
			});
		

	}

	public void saveMeetingParam(int eventId, int stallCount, int fees, int pc) {
		// TODO Auto-generated method stub
		String query = "update stallEvent set stallNo = ?, fees = ?, particpantCount = ? where id = ? ";	
		template.update(query, ps->{
			ps.setInt(1,stallCount);
			ps.setInt(2,fees);
			ps.setInt(3,pc);
			ps.setInt(4,eventId);
		});
	}

	
	public void updateManagerNotification(int eventId) {
		
		String query = "update stallEvent set M_NSent = 1 where id = ? ";
		template.update(query, (ps)->{
			ps.setInt(1,eventId);
		});
	}

	public List<StallEventDto> fetchEventNotification() {
		String query = "select * from stallEvent where M_NSent = 1 order by M_Nseen, M_NsentTime";
		
		return template.query(query, (rs,column)->{
			StallEventDto stallEventDto = new StallEventDto();
			stallEventDto.setId(rs.getInt("id"));
			stallEventDto.setEventName(rs.getString("eventName"));
			stallEventDto.setParticipantCount(rs.getInt("particpantCount"));
			stallEventDto.setSportType(rs.getString("sport"));
			stallEventDto.setIsNotificationSeen(rs.getInt("M_Nseen"));
			stallEventDto.setNotificationProgress(rs.getInt("M_Nprogress"));
			return stallEventDto;
		});
	}

	public void updateManagerTimelineProgress(int eventId) {
		// TODO Auto-generated method stub
		String query = "update stallEvent set M_Nseen = 1, M_Nprogress = 1 where id = ? ";
		template.update(query, (ps)->{
			ps.setInt(1,eventId);
		});
	}

	public void updateTheScheme(int eventId, String type) {
		// TODO Auto-generated method stub
		String sqlQuery;
		if(type.equals("Pre")){
			sqlQuery = "update stallEvent set preEventScheme = 1 where id = ? ";
		}
		else{
			sqlQuery = "update stallEvent set postEventScheme = 1 where id = ? ";
		}
		
		template.update(sqlQuery, (ps)->{
			ps.setInt(1,eventId);
		});
		
	}
	
	
}
