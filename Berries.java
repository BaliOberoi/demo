/*
 
 This class created the ball object, which loads and draws its image, and checks if the image is being clicked by the user
 
*/

package shopObjects;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;

public class Berries {
	private BufferedImage img;
	PVector pos;
	int scale = 192;
	
	private Rectangle2D.Double ballOutline;
	
	public Berries(PVector pos ) {
		//load the images for the ball image
		img = ImageLoader.loadImage("assets/berries.png");
		
		//set the position for the ball object
		this.pos = pos;
		
		//initializing the outline of the outline for the ball object
		ballOutline = new Rectangle2D.Double(-img.getWidth()/2, -img.getHeight()/2, img.getWidth(), img.getHeight());
		
	}
	
	//method that draws the ball object
	public void drawBerries(Graphics2D g2) {	
		AffineTransform tr = new AffineTransform();
		g2.translate(pos.x, pos.y);
		g2.scale(0.10, 0.10);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(tr);
	}
	
	//method that checks when the ball object is clicked
	public boolean clickedBerries(MouseEvent e, double x, double y){
		AffineTransform tr = new AffineTransform();
		tr.translate(pos.x, pos.y);  
		tr.scale(0.10, 0.10);
		Shape transformedBody = tr.createTransformedShape(ballOutline);
		return (transformedBody.contains(e.getX(), e.getY()));
	}
}
