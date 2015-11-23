package com.worksap.stm.SWARMS.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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
	
	public static String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = new Date(); // Or where ever you get it from
	    String res = dateFormat.format(date);
	    return res;
	}
	
	public static float getRandomFloat(int min, int max){
		 	Random rand = new Random();
		    //int max = 30, min = 1;
		    int n = rand.nextInt((max-min)*100 + 1);
		    return (float)(n+min*100)/(float)100;
	}
	
}
