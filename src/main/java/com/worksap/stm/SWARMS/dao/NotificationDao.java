package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.NotificationDto;

@Repository
public class NotificationDao{
	
	@Autowired
	private JdbcTemplate template;

	public static String INSERT_NOTIFICATION = "INSERT INTO notification (username, message) VALUES(?,?)";
	public static String GET_NOTIFICATION = "SELECT * FROM notification ORDER BY seen, post_time ";
	public static String DELETE_NOTIFICATION = "DELETE FROM notification where id = ?";
	public static String UPDATE_SEEN_STATUS =  "UPDATE notification set seen = 1, post_time = post_time where id = ?";

	
	
	public void insertNotification(String username, String message){
		template.update(INSERT_NOTIFICATION, (ps)->{
			ps.setString(1,username);
			ps.setString(2,message);
		});
	}
	
	public  List<NotificationDto> getNotification() throws IOException{
	
		try{
		return template.query(GET_NOTIFICATION, (rs,column)->{
			NotificationDto notificationDto = new NotificationDto();
			notificationDto.setId(rs.getInt("id"));
			notificationDto.setMessage(rs.getString("message"));
			notificationDto.setUsername(rs.getString("username"));
			notificationDto.setSeen(rs.getInt("seen"));
			notificationDto.setTimesStamp(rs.getString("post_time"));
			return notificationDto;
		});
	} catch(Exception e){
		
		System.out.println("At NotificationDao " + e);
		return null;
	}
		
		
	}

	public void deleteNotification(int nid) {
		// TODO Auto-generated method stub
		template.update(DELETE_NOTIFICATION, (ps)->{
			ps.setInt(1,nid);
		});
		
	}

	public void updateSeenStatus(int nid) {
		// TODO Auto-generated method stub
		template.update(UPDATE_SEEN_STATUS, (ps)->{
			ps.setInt(1,nid);
		});
	}

	public List<NotificationDto> getNotificationByUsername(String user) {
		
		String GET_NOTIFICATION_BY_USER = "SELECT * FROM notification WHERE username = ? ORDER BY seen, post_time";
			return template.query(GET_NOTIFICATION_BY_USER, (rs,column)->{
				NotificationDto notificationDto = new NotificationDto();
				notificationDto.setId(rs.getInt("id"));
				notificationDto.setMessage(rs.getString("message"));
				notificationDto.setUsername(rs.getString("username"));
				notificationDto.setSeen(rs.getInt("seen"));
				notificationDto.setTimesStamp(rs.getString("post_time"));
				return notificationDto;
			}, user );
		
	}
}
