package demos;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class myApplet extends PApplet {
	
	PImage img;
	public void setup()
	{
		
		img = loadImage("palmTrees.jpg", "jpg");
		size(img.width, img.height);
		//img.resize(0, height);
		//background(img);
		
	}
	int r = 0;
	int g = 0;
	int b = 0;
	public void draw()
	{
		getGreen();
		img.resize(0, height);
		image(img, 0, 0);
		fill(r, g, b);
		ellipse(width/4, height/5, width/7, width/7);
	}
	public void getGreen()
	{
		//Random rand = new Random();
		//return rand.nextInt(255);
		if (r < 254)
			r++;
		else if (g < 254)
			g++;
		else if (b < 254)
			b++;
		else
			r = 0;
	}

}
