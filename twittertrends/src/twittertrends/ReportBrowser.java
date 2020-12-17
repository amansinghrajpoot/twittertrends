package twittertrends;

import java.awt.Desktop;
import java.io.IOException;
import java.io.File;
import java.net.URI;

public final class ReportBrowser {
  
	   
		 
    static void createWindow(String chart) {   
    	  
    	System.out.println("*** Opening Chart in the Browser ***");
    	
    	File file = new File(System.getProperty("user.dir")+"//"+"Chart.html");
    	URI uri = file.toURI();
    	System.out.println(uri);
  
        	
            try {
            	if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            	    Desktop.getDesktop().browse(uri);
            	}  //opens HTML charts in browser
            	else {
            		 System.out.println("Couldn't open chart in Browser "+ file.getName());
            	}
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Couldn't open chart in Browser "+ file.getName());
            }
      
            System.out.println("*** Finished ***");
    	    
    }  
	
	
}
