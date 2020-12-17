package twittertrends;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Classifier {
	
	public static double classify(double[] vector) {
		double c = 0.0;
		ArrayList<Double> arrl = new  ArrayList<>();		
		double[] w = null;
		
		
		String temp;
		File file = new File(System.getProperty("user.dir")+"//"+"w");
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			 br = new BufferedReader(fr);
			
			while((temp = br.readLine()) != null || (temp != " ")) {
				
				if(temp == null) break;
					
				arrl.add(Double.parseDouble(temp));
				
			}
			
			w = new double[arrl.size()];
			
			for(int j =0; j< arrl.size(); j++) {
				w[j] = arrl.get(j);
			}
			
		
				} catch ( NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
            System.out.println("Terminated abnormally");
            System.exit(1);;
		}
		
	/****************************************************************************************************************/
		
		double logit = 0.0;
		for (int i=0; i<w.length;i++)  {
			logit += w[i] * vector[i];
		}
		
		c =  (1.0 / (1.0 + Math.exp(-logit)));
		return c;
		
		
		
	}

}
