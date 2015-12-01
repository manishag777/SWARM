package com.worksap.stm.SWARMS.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FutureEventEntity {
	int eventId;
	String startDate;
	String endDate;
}
