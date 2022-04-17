/* 
 
 This class draws the elements that are going to be in the background of the tennis shop application. It draws the tennis shop background, 
 it draws the sun geom, the rectangle that takes the strings off the racquet once its clicked, 
 and checks if the bag of the suop background is clicked as well as the rectangle button.
 
*/

package shopObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import util.ImageLoader;

public class SmoothieShop {
	//declaring the image for the tennis shop background
	private BufferedImage img;
	
	//declaring the x and y coordinates of the bag that is on the background image
	private double ballXPos;
	private double ballYPos;
	
	//declaring and initializing the scale for the bag that is on the background image
	private double circleScale = 160;
	
	/*declaring and initializing the values for the position x and y as well as the width and height of
	the rectangle that takes the strings off the racquet */
	private float rectXPos = 440;
	private float rectYPos = 350;
	private float rectWidth = 90;
	private float rectHeight = 30;
	
	//declaring the round rectangle geom that will take the strings off the racquet once the user clicks on it
	private RoundRectangle2D.Float roundRect;
	
	//declaring the ellipse geom that acts as a sun in the background 
	private Ellipse2D.Double sun;
	
	public SmoothieShop(String file, double x, double y) {
		//loading the image for the background
		img = ImageLoader.loadImage(file);
		
		ballXPos = x;
		ballYPos = y;
		
		//TO TAKE OUT THE STRINGS
		//initializing the round rectangle geom
		roundRect = new RoundRectangle2D.Float(rectXPos, rectYPos, rectWidth,rectHeight,5,5);
		
		//initializing the ellipse geom
		sun = new Ellipse2D.Double(850,30, 40, 40);
	}
	
	//method that draws the background of the shop
	public void drawShop(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.scale(1, 1);
		g2.drawImage(img, 0,0,img.getWidth(), img.getHeight(), null);
		g2.setTransform(at);
	}
	
	//method that draws the sun
	public void drawSun(Graphics2D g) {
		AffineTransform at = g.getTransform();
		g.setColor(Color.yellow);
		g.fill(sun);
		g.setTransform(at);
	}
	
	//method that draws the round rectangle that will act as a button to take the strings off the racquet
	public void drawRectangleButton(Graphics2D g) {
		AffineTransform at = g.getTransform();
		g.setColor(Color.BLACK);
		g.draw(roundRect);
		g.setColor(Color.GRAY);
		g.fill(roundRect);
		g.setTransform(at);
	}
	
//	method that checks when the bag of the background is being clicked
	public boolean circleIsClicked(double x, double y){
		boolean clicked = false;
		
		//if the user clicks on the following coordinates on the panel, the bag is being clicked
		if( x >= ballXPos && x <= ((double) ballXPos + circleScale) &&
			y >= ballYPos && y <= ((double) ballYPos + circleScale)) {
			clicked = true;
			
		}
		return clicked;
	}
	
	/*method that checks when the round rectangle that will act as a button to take the strings off the racquet is
	being clicked */
	public boolean rectButtonClicked(double x, double y) {
		boolean isClicked = false;
		if( x >= rectXPos && x <= ((double) rectXPos + (rectWidth)) &&
			y >= rectYPos && y <= ((double) rectYPos + (rectHeight))) {
			isClicked = true;
		}
		return isClicked;
	}
}