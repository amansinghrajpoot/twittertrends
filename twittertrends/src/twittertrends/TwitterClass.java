package twittertrends;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import twitter4j.*;

public class TwitterClass {
	

	public static Twitter getSession(){
		
		Properties prop = new Properties();
		
	    File file = new File(System.getProperty("user.dir")+"//"+"twitter4j.properties");
	    try{
       
	    	if(!file.exists()) throw new Exception();
	    	
	    }catch(Exception e) {
	      System.out.println("Properties file not found!!");
	      System.exit(1);
	    }
	    
	    
	    Twitter twitter = TwitterFactory.getSingleton();
		return twitter;
		
	}
	
	public static ArrayList<String> getTop10Trends(Twitter twitter, int locationwoeid) {
		
		try {
			Trends trends = twitter.getPlaceTrends(locationwoeid);
			
			ArrayList<String> trendslist = new ArrayList<String>();
			
			for(int i = 0; i < trends.getTrends().length; i ++) {
				
				trendslist.add(trends.getTrends()[i].getName());
			    }
			
			return trendslist;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			System.exit(1);
			
		}
		return null;
		
	}
	
	public static ArrayList<Status> getTrendingTweets(Twitter twitter, ArrayList<String> trendslist){
		
		ArrayList<Status> statuslist = new ArrayList<Status>();
		QueryResult result = null;
		Query[] query = new Query[trendslist.size()];
		int k = 0;
        
		for(String i : trendslist) {
			
			query[k] = new Query("#"+i);
			k += 1;
		}
        
        
        
		try {
			
			    for( int i = 0; i < query.length; i ++) {
			               
			    	      result = twitter.search(query[i]);
			
                          for (Status status : result.getTweets()) {
                        	  
                        	  statuslist.add(status);
                               }
        
			    }
			            
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		
		return statuslist;
				
	}
	
	public static void writeTweetsintofile(String date, File tweetsfile, Twitter twitter, String locationwoeid) throws Exception {
		
		BufferedWriter bw = null;
		FileWriter out = null;	
		
		out = new FileWriter(tweetsfile);
		bw = new BufferedWriter(out);
	    bw.write(date+"\n");
		bw.flush();
	
		ArrayList<String> trendslist = TwitterClass.getTop10Trends(twitter, Integer.parseInt(locationwoeid));
		ArrayList<Status> statuslist =  TwitterClass.getTrendingTweets(twitter, trendslist);
		
		for(Status status : statuslist) {
			
			bw.write(status.getText()+"\n");
			bw.flush();
		}
		
		try {
			bw.close();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	
	
}
