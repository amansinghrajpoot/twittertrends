package twittertrends;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Charts {
	
	public static String prepareTrendData(JSONObject tweetsfile) {
		
		JSONObject hashtag = (JSONObject) tweetsfile.get("hashtag");
		JSONArray hashtagarr = (JSONArray) hashtag.get("hashtags");
		
		String trendData = null;
		
		try {
			System.out.println("Fetching Twitter's Trending Hashtags");
			
			String filedate = (String) hashtag.get("date");
			System.out.println("--------------------------------------- "+"Fetched Trending topic for date "+filedate+" ---------------------------------------");
			StringBuilder sbd = new StringBuilder();	        
	        @SuppressWarnings("rawtypes")
			Iterator hasharrit = hashtagarr.iterator();
	        
	        JSONObject arrobj;
	        
	       while (hasharrit.hasNext()) {
	    	   
	    	  arrobj = (JSONObject) hasharrit.next();
	    	  @SuppressWarnings("rawtypes")
			  Set keys = arrobj.keySet();
	    	  @SuppressWarnings("rawtypes")
			  Iterator it = keys.iterator();
	    	  String key = (String) it.next();

	    	  if( Integer.parseInt( arrobj.get(key).toString()  ) != -1) {
	        		
	        		sbd.append("{ label: \""+ key +"\", y: "+ arrobj.get(key) +"},\n");
	        	}
	        	
	        }
	        
	        sbd.deleteCharAt(sbd.length() -2);
	        trendData = sbd.toString();
	        						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			
			System.exit(1);
		}
		System.gc();
		return trendData;
		
		
		
	}
	
public static String prepareHashtagData(JSONObject tweetsfile) {
		
		
		String hashtagData = null;
		JSONObject hashtag = (JSONObject) tweetsfile.get("hashtag");
		JSONArray hashtagarr = (JSONArray) hashtag.get("hashtags");
		
		try {
			
			
			StringBuilder sbd = new StringBuilder();
            
			@SuppressWarnings("rawtypes")
			Iterator hasharrit = hashtagarr.iterator();
	        
	        JSONObject arrobj;
	        
	        while (hasharrit.hasNext()) {
		    	   
		    	  arrobj = (JSONObject) hasharrit.next();
		    	  @SuppressWarnings("rawtypes")
				  Set keys = arrobj.keySet();
		    	  @SuppressWarnings("rawtypes")
			    	Iterator it = keys.iterator();
		    	    String key = (String) it.next();
	        		
	        		sbd.append("{ label: \""+ key +"\", y: "+ key.length() +"},\n");
	        	        	
	        }
	        
	      
	        sbd.deleteCharAt(sbd.length() -2);
	        hashtagData = sbd.toString();
	        			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			
			System.exit(1);
		}
		
		return hashtagData;
		
	}
	
	public static String prepareTrendsChart(String trendData, String hashtagData) {
		
		String chart = "<!DOCTYPE HTML>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"  <script type=\"text/javascript\">\n" + 
				"  window.onload = function () {\n" + 
				
				"  CanvasJS.addColorSet(\"Shades\",\n" + 
				"                [//colorSet Array\n" + 
				"\n" + 
				"                \"#488f31\",\n" + 
				"                \"#83af70\",\n" + 
				"                \"#bad0af\",\n" + 
				"                \"#f1f1f1\",\n" + 
				"                \"#f0b8b8\",\n" + 
				"                \"#e67f83\",\n" + 
				"                \"#346888\",\n" + 
				"                \"#5886a5\",\n" + 
				"                \"#9dc6e0\"                \n" + 
				"                ]);"+
				
				"  CanvasJS.addColorSet(\"blueShades\",\n" + 
				"                [//colorSet Array\n" + 
				"\n" + 
				"                \"#004c6d\",\n" + 
				"                \"#346888\",\n" + 
				"                \"#5986a5\",\n" + 
				"                \"#7ca6c2\",\n" + 
				"                \"#9fc6e0\",\n" + 
				"                \"#9dc6e0\"                \n" + 
				"                ]);"+
				
				"    var chart = new CanvasJS.Chart(\"chartContainer1\", {\n" + 
				
				"\n" + 
				"      animationEnabled: true,"+
				"      colorSet: \"Shades\","+
				"      title:{\n" + 
				"        text: \"Twitter Trending Hashtag Tweet Count\"              \n" + 
				"      },\n" + 
				"      data: [//array of dataSeries              \n" + 
				"        { //dataSeries object\n" + 
				"\n" + 
				"         /*** Change type \"column\" to \"bar\", \"area\", \"line\" or \"pie\"***/\n" + 
				"         type: \"doughnut\",\n" + 
				"         dataPoints: [\n" + 
				               trendData+
				"         ]\n" + 
				"       }\n" + 
				"       ]\n" + 
				"     });\n" + 
				
				"    var chart2 = new CanvasJS.Chart(\"chartContainer2\", {\n" + 
				"\n" + 
				"      animationEnabled: true,"+
				"      colorSet: \"blueShades\","+
				"      title:{\n" + 
				"        text: \"Trending Hashtags\"              \n" + 
				"      },\n" + 
				
				  
				"      data: [//array of dataSeries              \n" + 
				"        { //dataSeries object\n" + 
				"\n" + 
				"         /*** Change type \"column\" to \"bar\", \"area\", \"line\" or \"pie\"***/\n" + 
				"         type: \"doughnut\",\n" +
				"            legendMarkerType: \"triangle\",\n" + 
				"            legendMarkerColor: \"green\",\n" + 
				"         dataPoints: [\n" + 
				               hashtagData+
				"         ]\n" + 
				"       }\n" + 
				"       ]\n" + 
				"     });\n" + 	

				"    var chart3 = new CanvasJS.Chart(\"chartContainer3\", {\n" + 
				"\n" + 
				"      animationEnabled: true,"+

				"      title:{\n" + 
				"        text: \"Trends\"              \n" + 
				"      },\n" + 
				"      data: [//array of dataSeries              \n" + 
				"        { //dataSeries object\n" + 
				"\n" + 
				"         /*** Change type \"column\" to \"bar\", \"area\", \"line\" or \"pie\"***/\n" + 
				"         type: \"splineArea\",\n" + 
				"          toolTipContent: \"{y} units\",\n" + 
				"				type: \"splineArea\",\n" + 
				"				markerSize: 5,\n" + 
				"				color: \"rgba(54,158,173,.7)\"," +
				"         dataPoints: [\n" + 
				               hashtagData+
				"         ]\n" + 
				"       }\n" + 
				"       ]\n" + 
				"     });\n" + 	
				
				
				"\n" + 
				"    chart.render();\n" + 
				"\n" + 
				"    chart2.render();\n" + 
				"\n" + 
				"    chart3.render();\n" + 
				
				"  }\n" + 
				"  </script>\n" + 
				"  <script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				
				"<div id=\"chartContainer1\" style=\"width: 50%; height: 500px;display: inline-block;\"></div>"+
				"<div id=\"chartContainer2\" style=\"width: 50%; height: 500px;display: inline-block;\"></div><br/>"+
				"<div id=\"chartContainer3\" style=\"width: 100%; height: 500px;display: inline-block;\"></div>"+
				
				"</body>\n" + 
				"</html>";
		   
		File areaChart = new File(System.getProperty("user.dir")+"//"+"Chart.html");
		try {
			FileWriter fw = new FileWriter(areaChart);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(chart);
			bw.flush();
			
			fw.close();
			bw.close();
			
			System.out.println("Trending topics chart created...");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
          System.out.println(e.getLocalizedMessage());
		}
		
		return chart;
	}

}
