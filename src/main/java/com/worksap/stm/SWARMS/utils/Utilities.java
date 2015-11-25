package com.worksap.stm.SWARMS.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
	
	public static int findDayDiffernce(String date1 , String date2){
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	    try {
			Date fdate2 = myFormat.parse(date2);
			Date fdate1 = myFormat.parse(date1);
			long diff = fdate1.getTime() - fdate2.getTime();
		    int daysCount =   (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    System.out.println("daysCount = " +daysCount );
		    return daysCount;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			 System.out.println("daysCount = exception thrown");
			 e.printStackTrace();
			 return 0;
		}
		
	}
	
}
