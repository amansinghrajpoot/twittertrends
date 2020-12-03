package twittertrends;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import twitter4j.*;

public class TwitterClass {
	

	public static Twitter getSession(){
		
		
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
	
	public static ArrayList<Trend> getTop10Trends(Twitter twitter, int locationwoeid) {
		
		try {
			Trends trends = twitter.getPlaceTrends(locationwoeid);
			
			ArrayList<Trend> trendslist = new ArrayList<Trend>();
			
			for(int i = 0; i < trends.getTrends().length; i ++) {
				
				trendslist.add(trends.getTrends()[i]);
			    }
			
			return trendslist;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			System.exit(1);
			
		}
		return null;
		
	}
	
	public static ArrayList<Status> getTrendingTweets(Twitter twitter, ArrayList<Trend> trendslist){
		
		ArrayList<Status> statuslist = new ArrayList<Status>();
		QueryResult result = null;
		Query[] query = new Query[trendslist.size()];
		int k = 0;
        
		for(Trend i : trendslist) {
			
			query[k] = new Query("#"+i.getName());
			query[k].setCount(100);
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
	
		ArrayList<Trend> trendslist = TwitterClass.getTop10Trends(twitter, Integer.parseInt(locationwoeid));
		
		bw.write(trendslist.size()+"\n");
		bw.flush();
		
        for(Trend trend : trendslist) {
			
			bw.write(trend.getName()+":"+trend.getTweetVolume()+"\n");
			bw.flush();
		}
		
		ArrayList<Status> statuslist =  TwitterClass.getTrendingTweets(twitter, trendslist);
		
		for(Status status : statuslist) {
			
			bw.write(status.getText()+"\n");
			bw.flush();
		}
		System.out.println("New tweet file generated for date: "+date+" !!");
		try {
			bw.close();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	
	
}
