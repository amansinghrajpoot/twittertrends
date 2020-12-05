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
	
	public static JSONObject getTrendingTweets(Twitter twitter, ArrayList<Trend> trendslist){
		
		JSONObject tweetobj = new  JSONObject();
		JSONArray  tweetsarr;
		
		QueryResult result = null;
		Query query = new Query();
		
		for(Trend i : trendslist) {
			
			query = new Query("#"+i.getName());
			query.setCount(500);			
			
			try {
			               
			    	      result = twitter.search(query);
			    	      tweetsarr = new JSONArray();
                          for (Status status : result.getTweets()) {
                        	  
                        	  tweetsarr.put(status.getText());
                               }
                 tweetobj.put(i.getName(), tweetsarr);      
			         
	        	} catch (TwitterException e) {
		 	// TODO Auto-generated catch block
			e.printStackTrace();
	     	}
	       
			
		}
		System.gc();
        
        
	
		
		return tweetobj;
				
	}
	
	public static void writeTweetsintofile(String date, File tweetsfile, Twitter twitter, String locationwoeid) throws Exception {
		
		JSONObject mainobject =  new JSONObject();
		JSONObject hashtagobj = new  JSONObject();
		JSONArray hashtagarr = new JSONArray();
				
		BufferedWriter bw = null;
		FileWriter out = null;	
		
		hashtagobj.put("date", date);
		
		out = new FileWriter(tweetsfile);
		bw = new BufferedWriter(out);
	  
	
		ArrayList<Trend> trendslist = TwitterClass.getTop10Trends(twitter, Integer.parseInt(locationwoeid));
		
		hashtagobj.put("hashtagcount",trendslist.size());
		
		
        for(Trend trend : trendslist) {
        	
			hashtagarr.put( new JSONObject().put( trend.getName(), trend.getTweetVolume()) );
		}
		
        hashtagobj.put("hashtags",hashtagarr);
        
                
        JSONObject tweetobj =  TwitterClass.getTrendingTweets(twitter, trendslist);
		
        mainobject.put("hashtag", hashtagobj);
        mainobject.put("tweet", tweetobj);
        
        String jsonfile = mainobject.toString();
        
        bw.write(jsonfile);
        bw.flush();
        
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
