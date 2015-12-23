package com.worksap.stm.SWARMS.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemplatMailDto {
	String tagText;
	String subjectText;
	String bodyText;
}
