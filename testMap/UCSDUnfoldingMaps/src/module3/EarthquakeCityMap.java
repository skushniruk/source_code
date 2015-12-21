package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;
import processing.core.PGraphics;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	private float big = 20f;
	private float medium = 10f;
	private float small = 5f;
	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    SimplePointMarker marker;
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    float circleSize;
	    int color;
	    PointFeature pf;
	    if (earthquakes.size() > 0) {
	        for (int i = 0; i < earthquakes.size(); i++)
	        {
	        	pf = earthquakes.get(i);
	        	Object magObj = pf.getProperty("magnitude");
		    	float mag = Float.parseFloat(magObj.toString());
	        	if (mag >= 5.0f)
	        	{
	        		circleSize = big;
	        		color = color(255, 0, 0);
	        	}
	        	else if (mag >= 4.0f)
	        	{
	        		circleSize = medium;
	        		color = color(255, 255, 0);
	        	}
	        	else
	        	{
	        		circleSize = small;
	        		color = color(0, 0, 255);
	        	}
	        	marker = createMarker(pf);
	        	marker.setRadius(circleSize);
	        	marker.setColor(color);
	        	markers.add(marker);
	        	
	        }
	    	/*System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	// PointFeatures also have a getLocation method
	    	System.out.println(earthquakes.size());*/
	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    
	    //mark = createMarker(f);
	    map.addMarkers(markers);
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
		return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		fill(255, 255, 204);
		rect(25, 50, 150, 250);
		fill(255, 0, 0);
		ellipse(45.0f, 125.0f, big, big);
		fill(255, 255, 0);
		ellipse(45.0f, 175.0f, medium, medium);
		fill(0, 0, 255);
		ellipse(45.0f, 225.0f, small, small);
		fill(0, 0, 0);
		text("Earthquake Key", 45.0f, 80.0f);
		text("5.0+ Magnitude", 65.0f, 129.0f);
		text("4.0+ Magnitude", 65.0f, 179.0f);
		text("Below 4.0", 65.0f, 229.0f);
	
	}
}
