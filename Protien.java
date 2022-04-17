/* 
 
 This class created the towel object, which loads and draws its image, and checks if the image is being clicked by the user
 
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

public class Protien {
	private BufferedImage img;
	PVector pos;
	
	private Rectangle2D.Double towelOutline;
	
	public Protien(PVector pos ) {
		//load the image of the towel
		img = ImageLoader.loadImage("assets/protien.png");
		
		//setting the position for the towel
		this.pos = pos;
		
		//set the outline for the towel image
		towelOutline = new Rectangle2D.Double(-img.getWidth()/2, -img.getHeight()/2, img.getWidth(), img.getHeight());
	}
	
	//method that draws the towel object
	public void drawProtien(Graphics2D g2) {	
		AffineTransform tr = new AffineTransform();
		g2.translate(pos.x, pos.y);
		g2.scale(0.15, 0.15);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(tr);
	}

	//method that checks when the towel object is clicked
	public boolean clickedProtien(MouseEvent e, double x, double y){		
		AffineTransform tr = new AffineTransform();
		tr.translate(pos.x, pos.y);  
		tr.scale(0.15, 0.15);
		Shape transformedBody = tr.createTransformedShape(towelOutline);
		return (transformedBody.contains(e.getX(), e.getY()));
	}
}
