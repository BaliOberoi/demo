package shopObjects;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;

public class Glass {
	private BufferedImage img;
	private Rectangle2D.Double gripOutline;
	PVector pos;
	
	public Glass(PVector pos ) {
		//load the image of the grip
		img = ImageLoader.loadImage("assets/glass.png");
		
		//set the position for the grip image
		this.pos = pos;
		
		//set the outline for the grip image
		gripOutline = new Rectangle2D.Double(-img.getWidth()/2, -img.getHeight()/2, img.getWidth(), img.getHeight());
	}
	
	//method draw the image of the grip
	public void drawGlass(Graphics2D g2) {	
		AffineTransform tr = new AffineTransform();
		g2.translate(pos.x, pos.y);
		g2.scale(0.10, 0.10);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(tr);
	}
	
	//method that checks if the outline of the grip is clicked
	public boolean clickedFruits(MouseEvent e, double x, double y){		
		AffineTransform tr = new AffineTransform();
		tr.translate(pos.x, pos.y); 
		tr.scale(0.10, 0.10);
		Shape transformedBody = tr.createTransformedShape(gripOutline);
		return (transformedBody.contains(e.getX(), e.getY()));
		
	}

}
