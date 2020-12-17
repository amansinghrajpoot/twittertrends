package twittertrends;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;

public final class TFIDF {
	
	private static String[] wordlist;

    public static double[] generate(String tweet, JSONArray doc) {
		
	     	double[] vector = null;
		    double tf = 0.0;
	    	double idf = 0.0;
		
				
	    
		vector = new double[wordlist.length];		
			
			for (int i = 0; i < wordlist.length ; i ++) {
	        	 
	        	 tf = TFIDF.calculateTF(tweet, wordlist[i]);
	        	 idf = TFIDF.calculateIDF(wordlist[i], doc);
	        	 
	        	 vector[i] = tf * idf;
	        	 
			}    		
		
		return vector;
	}
	
	
public static void getWords() {
		
	File dictionary = new File (System.getProperty("user.dir")+"//"+ "dictionary");

	FileReader fr = null;
	BufferedReader br = null;
	String[] wordsetarray = null;
	
	try {
		fr = new FileReader(dictionary);
		br = new BufferedReader(fr);
		
	    wordsetarray = br.readLine().split(" ");
	} catch ( IOException e) {
		// TODO Auto-generated catch block
          System.out.println(e.getLocalizedMessage());
	}
	
	wordlist = wordsetarray;
	
//    	HashSet <String> wordset = new HashSet<String>();
//    	String strbd = null;
//    	Collection<String> allwords = null;
//        ArrayList<String> words = null;
//    	String[] stopwords = {"a", "about","above", "therefore","midnight", "after", "again", "against", "ain", "all", "am", "an", "and", "any", "are", "aren", "arent", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can", "couldn", "couldnt", "d", "did", "didn", "didnt", "do", "does", "doesn", "doesnt", "doing", "don", "dont", "down", "during", "each", "few", "for", "from", "further", "had", "hadn", "hadnt", "has", "hasn", "hasnt", "have", "haven", "havent", "having", "he", "her", "here", "hers", "herself", "him", "himself", "his", "how", "i", "if", "in", "into", "is", "isn", "isnt", "it", "its", "its", "itself", "just", "ll", "m", "ma", "me", "mightn", "mightnt", "more", "most", "mustn", "mustnt", "my", "myself", "needn", "neednt", "no", "nor", "not", "now", "o", "of", "off", "on", "once", "only", "or", "other", "our", "ours", "ourselves", "out", "over", "own", "re", "s", "same", "shan", "shant", "she", "shes", "should", "shouldve", "shouldn", "shouldnt", "so", "some", "such", "t", "than", "that", "thatll", "the", "their", "theirs", "them", "themselves", "then", "there", "these", "they", "this", "those", "through", "to", "too", "under", "until", "up", "ve", "very", "was", "wasn", "wasnt", "we", "were", "weren", "werent", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "won", "wont", "wouldn", "wouldnt", "y", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "could", "hed", "hell", "hes", "heres", "hows", "id", "ill", "im", "ive", "lets", "ought", "shed", "shell", "thats", "theres", "theyd", "theyll", "theyre", "theyve", "wed", "well", "were", "weve", "whats", "whens", "wheres", "whos", "whys", "would"};
//		
//		ArrayList<String> stoplist = new ArrayList<String>();
//		for(int i = 0; i < stopwords.length; i ++) {
//			stoplist.add(stopwords[i]);
//		}
//    	
//    	@SuppressWarnings("unchecked")
//		Iterator<String> it = arrlist.iterator();
//    	
//    	while ( it.hasNext()) {
//    		  		
//    		strbd =  it.next();
//    		
//    		strbd = strbd.replaceAll( "[^a-zA-Z ]", "").toLowerCase();
//			
//			allwords =  Stream.of(strbd.split(" ")).collect(Collectors.toCollection( ArrayList<String>::new));
//			allwords.removeAll(stoplist);
//    		
//			words = (ArrayList<String>) allwords;
//			
//			for( int i = 0; i < words.size(); i ++) {
//		    	
//		    	wordset.add(words.get(i));
//		    }
//    		
//    	}
//    	
//    	System.gc();
//    	
//    	String[] wordsetarray = new String[wordset.size()];
//    	Iterator<String> wsit = wordset.iterator();
//    
//    	for ( int i = 0; i < wordset.size(); i ++) {
//    		
//    		wordsetarray[i] = wsit.next();
//    	}
    	
    }
	
   private static double calculateTF(String tweet, String word) {
		
		double tf = 0.0;
		double wordcount = 0.0;
		double tfcount = 0.0;
		
		String[] tweetwords = tweet.split(" ");
		
		wordcount = (double) tweetwords.length;
		
		for(int i = 0; i < tweetwords.length ; i ++) {
			
			if( tweetwords[i].equals(word)) {
				tfcount = tfcount + 1.0;
			}
		 }
		
		tf = (tfcount/wordcount);
		
		return tf;
    	}
	
     private static double calculateIDF(String word, JSONArray arrlist) {
		
		double idf = 0.0;
		double ND = 1.0;
		double NF = 1.0;
		
		@SuppressWarnings("unchecked")
		Iterator<String> it = arrlist.iterator();
		String tweet = null;
		String[] tweetwords = null;

		ND = arrlist.size();
		
		while (it.hasNext()) {
			
			tweet = it.next();
			
			tweetwords = tweet.split(" ");
			   for ( int i = 0; i < tweetwords.length; i ++ ) {
				   
				   if( tweetwords[i].equals(word)) {
					   
					   NF = NF + 1.0;
					   break;
					   
				     }
			   }
			
			}
		
		idf = Math.log( 1 + (ND/NF));
		
		return idf;
	  }
}


