package twittertrends;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Charts {
	
	public static String prepareTrendData(File tweetsfile) {
		
		BufferedReader br = null;
		FileReader in = null;
		String trendData = null;
		
		try {
			System.out.println("Fetching Twitter's Trending topics");
			in = new FileReader(tweetsfile);
			br = new BufferedReader(in);
			String filedate = br.readLine();
			System.out.println("--------------------------------------- "+"Fetched Trending topic for date "+filedate+" ---------------------------------------");
			StringBuilder sbd = new StringBuilder();
	        int topicCount = Integer.parseInt(br.readLine());
	        
	        
			
			in.close();
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			
			System.exit(1);
		}
		
		return trendData;
		
	}
	
	public static void prepareTrendsChart(String trendData) {
		
		File areaChart = new File(System.getProperty("user.dir")+"//"+"areaChar.html");
		
		
		String areachart = "<!DOCTYPE HTML>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"  <script type=\"text/javascript\">\n" + 
				"  window.onload = function () {\n" + 
				"    var chart = new CanvasJS.Chart(\"chartContainer\", {\n" + 
				"\n" + 
				"      title:{\n" + 
				"        text: \"Trending Tweets Count\"              \n" + 
				"      },\n" + 
				"      data: [//array of dataSeries              \n" + 
				"        { //dataSeries object\n" + 
				"\n" + 
				"         /*** Change type \"column\" to \"bar\", \"area\", \"line\" or \"pie\"***/\n" + 
				"         type: \"area\",\n" + 
				"         dataPoints: [\n" + 
				               trendData+
				"         ]\n" + 
				"       }\n" + 
				"       ]\n" + 
				"     });\n" + 
				"\n" + 
				"    chart.render();\n" + 
				"  }\n" + 
				"  </script>\n" + 
				"  <script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"  <div id=\"chartContainer\" style=\"height: 300px; width: 100%;\">\n" + 
				"  </div>\n" + 
				"</body>\n" + 
				"</html>";
		
	}

}
