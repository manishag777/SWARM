package com.worksap.stm.SWARMS.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {
	
	public static java.sql.Date formatDate(String date){
		
		try{
		if(date=="null" || date.equals(""))
			return null;
		String param[] = date.split("/");
		return java.sql.Date.valueOf(param[2]+"-"+param[0]+"-"+param[1]);
		}
		catch(Exception e){
			return null;
		}
	}
public static String formatString(String x){
	if(x=="null" || x.equals(""))
		return null;
	
	else return x;
}
}
