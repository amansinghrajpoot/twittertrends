package twittertrends;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import twitter4j.*;

public class TwitterDriver {

	
	public static void main(String args[])  {
		
		
				
		String locationwoeid = null;
		org.json.simple.JSONObject tweetreader = null;
		
		Twitter twitter = TwitterClass.getSession();
		
		File twitter4jproperties = new File(System.getProperty("user.dir")+"//"+"twitter4j.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(twitter4jproperties);
			Properties prop = new Properties();
			prop.load(fis);
		    locationwoeid = prop.getProperty("locationwoeid");
		    fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Properties file not found!!");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Something went wrong while reading the properties file!!");
		}
		
		File tweetsfile = new File(System.getProperty("user.dir")+"//"+"tweets.json");
		JSONParser jp = new JSONParser();
		
		
		
		try {
			
		 tweetreader = (org.json.simple.JSONObject) jp.parse(new FileReader(tweetsfile));
		} catch (IOException | org.json.simple.parser.ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getLocalizedMessage());
			System.exit(1);
		}
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		if (!tweetsfile.exists()) {
			

				
				try {
					
					TwitterClass.writeTweetsintofile(date, tweetsfile, twitter, locationwoeid);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getLocalizedMessage());
				}
				
			
		}
		else {
			
			try {
			
				JSONObject hashtagobj  =  (JSONObject) tweetreader.get("hashtag") ;
				String filedate = (String) hashtagobj.get("date");
				
				Date d1 = dateFormat.parse(filedate);
				Date d2 = dateFormat.parse(date);
				
				if(d1.before(d2)) {
					
					try {
						
						TwitterClass.writeTweetsintofile(date, tweetsfile, twitter, locationwoeid);
					
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getLocalizedMessage());
					}
				    
				}
				else {
					
					System.out.println("File already exists for date: "+date+" !!");
				}
				
								// TODO Auto-generated catch block
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getLocalizedMessage());
			}
						
		    }
		
		  String trendData = Charts.prepareTrendData(tweetreader);
		  String hashtagData = Charts.prepareHashtagData(tweetreader);
		  
		  String chart = Charts.prepareTrendsChart(trendData, hashtagData);
		 
		  ReportBrowser.createWindow(chart);
		
		
	} //main method
}  //class
