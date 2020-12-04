package twittertrends;

import java.awt.Desktop;
import java.io.IOException;
import java.io.File;
import java.net.URI;

public class ReportBrowser {
  
	   
		 
    static void createWindow(String chart) {   
    	  

    	
    	File file = new File(System.getProperty("user.dir")+"//"+"Chart.html");
    	URI uri = file.toURI();
    	System.out.println(uri);
  
        	
            
        	
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e.getLocalizedMessage());
            }
      
        
    	    
    }  
	
	
}
