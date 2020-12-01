package twittertrends;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	
}
