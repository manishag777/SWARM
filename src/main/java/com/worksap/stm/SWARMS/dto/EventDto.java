package com.worksap.stm.SWARMS.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDto {
	
	String eventName;
	String eventDetail;
	String eventDate;
	List<String> sportId;
	List<String> storeId;
	
}
