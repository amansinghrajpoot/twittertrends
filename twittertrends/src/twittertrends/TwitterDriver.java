package twittertrends;

import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.*;

public class TwitterDriver {

	
	public static void main(String args[]) throws TwitterException  {
		
		Twitter twitter = TwitterClass.getSession();
		
		Trends trends = twitter.getPlaceTrends(23424848);
		for (int i = 0; i < trends.getTrends().length; i++) {
		    System.out.println(trends.getTrends()[i].getName());
		}

	}
}
