/* 
 
   This class created the string machine object, which loads and draws its image, checks what state of the string machine 
   is on to be able to draw it in the appropiate event check, and checks if the image is being clicked by the user, 
   and also checks if it collides with the racquet object
 
*/

package shopObjects;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Jug.Jug;
import processing.core.PVector;
import util.ImageLoader;

public class Blender {
	
	private BufferedImage img;
	private BufferedImage imgMachineEmpty;
	private BufferedImage imgMachineRacquet;
	boolean machineWithRacquet;
	
	private Rectangle2D.Double outlineRect;
	PVector pos;
	
	public Blender(PVector pos) {
		//load the images for the string machine
		imgMachineEmpty = ImageLoader.loadImage("assets/blender.png"); // string machine without the racquet on it
		imgMachineRacquet = ImageLoader.loadImage("assets/filledJug.png"); // string machine with the racquet on it
		
		//instantiate the outline for the string machine image
		outlineRect = new Rectangle2D.Double(-imgMachineRacquet.getWidth()/4,  -imgMachineRacquet.getHeight()/4, imgMachineRacquet.getWidth()/2, imgMachineRacquet.getHeight()/2);
		
		//set the position for the string machine
		this.pos = pos;
	}
	
	//Method that draws the string machine
	public void drawStringMachine(Graphics2D g2) {
		//if statement that checks if the racquet image is the one with the racquet or the one without the racquet
		if (machineWithRacquet == false) { 
			//the racquet image is the one that does not have the racquet with it
			img = imgMachineEmpty;
		} else {
			//the racquet image is the one that does have the racquet with it
			img = imgMachineRacquet;
		}
		
		AffineTransform at = g2.getTransform();
		g2.translate(pos.x, pos.y);
		g2.scale(0.50, 0.50);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}
	
	//setter method for the change in the machine state
	public void setMachineState(boolean machineState) {
		machineWithRacquet = machineState;
	}
	
	//getter method to get the machine state
	public boolean getMachineState() {
		return machineWithRacquet;
	}
	
	//method that sets the outline for the string machine image
	public Shape getOutline() {
        AffineTransform at = new AffineTransform();		
		at.translate(pos.x, pos.y);
		return at.createTransformedShape(outlineRect);
    }
	
	//method that checks the collision with the racquet object
	public boolean collision(Jug racquet) {
		if(racquet.getOutline().intersects(getOutline().getBounds2D()) &&
		    getOutline().intersects(racquet.getOutline().getBounds2D())) {
			return true;
		} else {
			return false;
		}
	}
	
	//method that checks if the outline of the string machine is clicked
	public boolean outlineIsClicked(MouseEvent e, double x, double y){
		
		AffineTransform tr = new AffineTransform();
		tr.translate(pos.x, pos.y);  
		Shape transformedBody = tr.createTransformedShape(outlineRect);
		return (transformedBody.contains(e.getX(), e.getY()));
	}
}


