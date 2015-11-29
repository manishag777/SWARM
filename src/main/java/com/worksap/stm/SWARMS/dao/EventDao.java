package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.EventDto;
import com.worksap.stm.SWARMS.utils.Utilities;


import org.springframework.jdbc.core.BatchPreparedStatementSetter;

@Repository
public class EventDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_ID =  "SELECT LAST_INSERT_ID() as id" ;
	private static final String INSERT_EVENT= "INSERT INTO sportevent"
			+ " (name, detail, fromDate, toDate)"
			+ " VALUES (?, ?, ?, ?)";
	
	private static final String INSERT_EVENT_SPORTS = "insert into event_sport (event_id, sport_id) values(?,?)";
	private static final String INSERT_EVENT_STORES = "insert into event_store (event_id, store_id) values(?,?)";

	
	public int insert(EventDto eventDto) throws IOException {
		try {
				template.update(INSERT_EVENT, (ps) -> {
				ps.setString(1, eventDto.getEventName());
				ps.setString(2, eventDto.getEventDetail());
				String dateArr[] = eventDto.getEventDate().split("-");
				ps.setDate(3, Utilities.formatDate(dateArr[0].trim()));
				ps.setDate(4, Utilities.formatDate(dateArr[1].trim()));
			});
				
				int id = template.queryForObject(
						 FETCH_ID, Integer.class);
				 updateSport(eventDto, id);
				 updateStore(eventDto, id);

			 

			 
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
	
}
