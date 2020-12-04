package twittertrends;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import twitter4j.*;

public class TwitterDriver {

	
	public static void main(String args[])  {
		
		String locationwoeid = null;
		BufferedReader br = null;
		FileReader in = null;
		
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
		
		File tweetsfile = new File(System.getProperty("user.dir")+"//"+"tweets.dat");
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
				in = new FileReader(tweetsfile);
				br = new BufferedReader(in);
				
				String filedate = br.readLine();
				
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
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getLocalizedMessage());	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getLocalizedMessage());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getLocalizedMessage());
			}
						
		    }
		
		  String trendData = Charts.prepareTrendData(tweetsfile);
		  String chart = Charts.prepareTrendsChart(trendData);
		 
		  ReportBrowser.createWindow(chart);
		
	} //main method
}  //class
