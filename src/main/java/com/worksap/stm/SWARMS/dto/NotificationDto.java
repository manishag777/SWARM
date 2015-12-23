package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDto {
	int id;
	String username;
	String message;
	int seen;
	String timesStamp;
	
}
