/* 
 
 This class created the dampener object, which loads and draws its image, and checks if the image is being clicked by the user
 
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

public class Greens {
	private BufferedImage img;
	PVector pos;
	
	private Rectangle2D.Double dampenerOutline;
	
	public Greens(PVector pos ) {
		//load the image of the dampener
		img = ImageLoader.loadImage("assets/greens.png"); 
		
		//set the position for the dampener image
		this.pos = pos;
		
		//set the outline for the dampener image
		dampenerOutline = new Rectangle2D.Double(-img.getWidth()/2, -img.getHeight()/2, img.getWidth(), img.getHeight());
	}
	
	//method that draws the dampener image
	public void drawGreens(Graphics2D g2) {	
		AffineTransform tr = new AffineTransform();
		g2.translate(pos.x, pos.y);
		g2.scale(0.10, 0.10);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(tr);
	}
	
	//method that checks if the outline of the dampener is clicked
	public boolean clickGreens(MouseEvent e, double x, double y){
		AffineTransform tr = new AffineTransform();
		tr.translate(pos.x, pos.y);  
		tr.scale(0.10, 0.10);
		Shape transformedBody = tr.createTransformedShape(dampenerOutline);
		return (transformedBody.contains(e.getX(), e.getY()));
	}
}
