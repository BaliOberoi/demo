/* 
 
 This class tracks the progress for the progress bar, as well as drawing the rectangles to let the user know what events are being done
 
*/

package shopObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import processing.core.PVector;

public class ProgressBar {
	
	private int progressBarCount; //declaring the integer for the amount of rectangles to be drawn in the progress bar
	private ArrayList <Rectangle2D> rect; //declaring a rectangle arraylist
	
	PVector pos; //Declaring the PVector por the position of the rectangle object
	
	public ProgressBar(PVector pos) {
		//set the position for the dampener image
		this.pos = pos;
		
		rect = new ArrayList <Rectangle2D> ();
		
		//running through the arraylist
		for(int i = 0; i < 7; i++)	{	
			//if the second rectangle is drawn, draw it to the left of the other rectangle by 42 pixels
			if(i > 0) {
				pos.x += 42;
			}
			//add the rectangle object
			rect.add(new Rectangle2D.Double(pos.x,pos.y,42,20));
			
		}
	}
	
	//method that draws the rectangles for the progress bar
	public void draw(Graphics2D g2) {
		
		for(int i = 0; i < progressBarCount; i++) {
			AffineTransform tr = new AffineTransform();
			g2.setColor(Color.red);
			g2.fill(rect.get(i));
			g2.setTransform(tr);
		}
	}
	
	//method that increased the value of the progress bar
	public void increaseProgressBar() {
		progressBarCount++;
	}
}
