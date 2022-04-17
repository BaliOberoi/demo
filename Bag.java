/*
 
 This class created the bag object, which loads and draws its image, and checks the state of the bag to be sure which one to draw in the appropiate event click
 
*/

package shopObjects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;

public class Bag {
	//declaring the images for the bag object
	private BufferedImage img;
	private BufferedImage oneItem;
	private BufferedImage twoItems;
	private BufferedImage threeItems;
	
	//declaring and initializing the state for the bag object
	private int state = 0;
	
	//declaring the PVector for the position of the bag object
	PVector pos;
	
	public Bag(PVector pos) {
		//load the image for the bag that contains just the ball image inside
		oneItem = ImageLoader.loadImage("assets/bag with 1.png");
		
		//load the image for the bag that contains the ball image and the towel image inside
		twoItems = ImageLoader.loadImage("assets/bag with 2.png");
		
		//load the image for the bag that contains the ball image, the towel image, and the dampener image inside
		threeItems = ImageLoader.loadImage("assets/bag with 3.png");
		
		//initializing the position for the bag object
		this.pos = pos;
	}
	
	//method that draws the bag objects
	public void drawBag(Graphics2D g2) {
		//if the state of the bag is 1, set the image to be the one that just contains the ball inside
		if(state == 1) {
			img = oneItem;
			
		//if the state of the bag is 2, set the image to be the one that contains the ball, and the towel images inside
		} else if(state == 2) {
			img = twoItems;
			
		//if the state of the bag is 1, set the image to be the one that contains the ball image, the towel image, 
		//and the dampener image inside
		} else if (state == 3) {
			img = threeItems;
		} 
		
		AffineTransform tr = new AffineTransform();
		g2.translate(650, 280);
		g2.scale(0.50, 0.50);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(tr);
	}
	
	//setter method that changes the state of the state of the bag object
	public void setState(int switchState) {
		state = switchState;
	}
	
	//getter method that gets the state of the bag objects
	public int getState() {
		return state;
	}
}
