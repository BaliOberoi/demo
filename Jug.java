/* this class created the racquet object, loads its image, draws it, checks its collision with the racquet by drawing and setting its outline*/

package Jug;


import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import processing.core.PVector;
import util.ImageLoader;

public class Jug {
	//Declaring the image objects
	private BufferedImage img;
	private BufferedImage img_2;
	private BufferedImage img_3;
	private BufferedImage img_4;
	private BufferedImage img_5;
	private BufferedImage img_6;
	private BufferedImage img_7;
	private BufferedImage img_8;
	private BufferedImage img_9;
	
	
	//declaring and initializing the each of the racquet states
	private int state = 0;
	private int broken = 1;
	private int empty = 2;
	private int berries = 3;
	private int fruits = 4; //fruits
	private int greens = 5; 
	private int protien = 6;
	private int ready = 7;
	private int glass = 8;
	
	
	//declaring the outline for the racquet objects
	private Rectangle2D.Double outlineRect;
	
	//declaring the PVector for the position of the racquet object
	PVector position;
	
	public Jug(PVector pos) {
		//load the image of the racquet that has broken strings and grip
		img_2 = ImageLoader.loadImage("assets/jug.png"); 
		
		//load the image of the racquet that has its strings removed
		img_3 = ImageLoader.loadImage("assets/jug.png");
		
		//load the image of the racquet that has new strings //berries
		img_4 = ImageLoader.loadImage("assets/berriesJug.png");
		
		//load the image of the racquet with new strings and grips //apples
		img_5 = ImageLoader.loadImage("assets/bApplesJug.png");
		
		img_6 = ImageLoader.loadImage("assets/baGreensJug.png");
		
		img_7 = ImageLoader.loadImage("assets/bagProtienJug.png");
		
		img_8 = ImageLoader.loadImage("assets/SmoothieDone.png");
		
		//img_9 = ImageLoader.loadImage("assets/glass.png");
		
		//initializing the outline of the outline for the racquet object
		outlineRect = new Rectangle2D.Double(-img_2.getWidth()/4,  -img_2.getHeight()/4, img_2.getWidth()/2, img_2.getHeight()/2);
		
		//initializing the position for the tennis object
		this.position = pos;
	}
	
	//method that draws the racquet object
	public void drawJug(Graphics2D g2) {
		//check if the state of the racquet is the one with the broken strings and grip
		if(state == broken) {
			//set the image to be the racquet with the broken strings and grip
			img = img_2;
			
			//--------STATE 2-------
		//check if the state of the racquet is the one without the strings
		}
		
		if(state == empty) {
			//set the image to be the racquet without the strings
			img = img_3;
			
			//--------STATE 3-------
		//check if the state of the racquet is the one with new strings
		} 
		
		if (state == berries) {
			//set the image to be the racquet with new strings
			img = img_4;
		
			//--------STATE 4-------
		//check if the state of the racqeut is the one with new grip
		} 
		
		if (state == fruits) {
			//set the image to be the racquet with new grip
			img = img_5;
		
			//--------STATE 5-------
		} 
		
		if (state == greens) {
			
			img = img_6;
		
		}
		
		if (state == protien) {
			img = img_7;
			//img = img_9;
			
			//--------STATE 7-------
		}
		
		if (state == ready) {
			
			img = img_8;
		}
		
		if (state == glass) {
			
		}
		
		AffineTransform tr = new AffineTransform();
		g2.translate(position.x, position.y);
		g2.scale(0.50, 0.50);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(tr);
	}
	
	//check when the user clicks on the racquet object to be able to drag it
	public boolean jugDragged(MouseEvent e) {

		AffineTransform tr = new AffineTransform();
		tr.translate(position.x, position.y);  
		Shape transformedBody = tr.createTransformedShape(outlineRect);
		return (transformedBody.contains(e.getX(), e.getY()));
	}
	
	//setter method for the position for the mouseDragged
	public void setLocation(PVector newLoc) {
		position = newLoc;
	}
	
	//setter method to set the state of the racquet to be able to change the state in the panel class
	public void setState(int switchState) {
		state = switchState;
	}
	
	//getter method to get the state of the racquet object
	public int getState() {
		return state;
	}
	
	//method that gets the outline of the racquet object for the collision with the string machine
	public Shape getOutline() {
        AffineTransform at = new AffineTransform();		
		at.translate(position.x, position.y);
		return at.createTransformedShape(outlineRect);
    }
	
	//method that updates the outline when the user released the mouse onto the string machine
	public void update() {
		getOutline();
	}
}
