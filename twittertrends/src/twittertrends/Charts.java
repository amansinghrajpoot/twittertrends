package twittertrends;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	        String topic[] = null;
	        
	        for( int i = 0; i < topicCount - 1; i ++) {
	        	topic = br.readLine().split(":");
	        	if( Integer.parseInt(topic[1]) != -1) {
	        		
	        		sbd.append("{ label: \""+ topic[0] +"\", y: "+ topic[1] +"},\n");
	        	}
	        	
	        }
	        
	        topic = br.readLine().split(":");
	        
	        if( Integer.parseInt(topic[1]) == -1) {
        		
        		topic[1] = "0";
        	}
	        
        	sbd.append("{ label: \""+ topic[0] +"\", y: "+ topic[1] +"}\n");
        
	        
	        trendData = sbd.toString();
	        			
			in.close();
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			
			System.exit(1);
		}
		
		return trendData;
		
	}
	
	public static String prepareTrendsChart(String trendData) {
		
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
				"        text: \"Twitter trending hashtags\"              \n" + 
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
				"        text: \"Trends\"              \n" + 
				"      },\n" + 
				
				  
				"      data: [//array of dataSeries              \n" + 
				"        { //dataSeries object\n" + 
				"\n" + 
				"         /*** Change type \"column\" to \"bar\", \"area\", \"line\" or \"pie\"***/\n" + 
				"         type: \"column\",\n" +
				"            legendMarkerType: \"triangle\",\n" + 
				"            legendMarkerColor: \"green\",\n" + 
				"            showInLegend: true,\n" + 
				"            legendText: \"Country wise population\","+
				"         dataPoints: [\n" + 
				               trendData+
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
				               trendData+
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
				"<div id=\"chartContainer3\" style=\"width: 100%; height: 400px;display: inline-block;\"></div>"+
				
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
