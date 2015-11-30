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
	private static final String INSERT_EVENT= "INSERT INTO sportevent"
			+ " (name, detail, fromDate, toDate)"
			+ " VALUES (?, ?, ?, ?)";
	
	private static final String INSERT_EVENT_SPORTS = "insert into event_sport (event_id, sport_id) values(?,?)";
	private static final String INSERT_EVENT_STORES = "insert into event_store (event_id, store_id) values(?,?)";
	private static final String FETCH_EVENT_LISTS = "select se.* from sportevent se inner join event_sport es on se.id = es.event_id inner join"
			+ " event_store est on se.id = est.event_id where es.sport_id = ? and est.store_id = ? and ((fromDate between ? and ?) or (toDate between ? and ?)) order by fromDate DESC ";

	public List<EventDto> fetchEventDto(String sport, String store, String fromDate, String toDate){
		
		return template.query(FETCH_EVENT_LISTS, new Object[]{sport,store,fromDate,toDate,fromDate,toDate}, (rs,column)->{
			EventDto eventDto = new EventDto();
			eventDto.setEventName(rs.getString("name"));
			eventDto.setEventDetail(rs.getString("detail"));
			eventDto.setFromDate(rs.getString("fromDate"));
			eventDto.setToDate(rs.getString("toDate"));
			eventDto.setId(rs.getInt("id"));
			//eventDto.setSportName(rs.getString("sport_id"));
			//eventDto.setStoreName(rs.getString("store_id"));
			return eventDto;
		});
		
	}; 
	
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
	
	
}
