/* 
 
 Final project created by Gabriela Farhat for the class IAT 265.
 The tree with recursion added is re-using the code of the project 3 I submitted for this class as well.


*/

package main;

import javax.swing.JFrame;

public class SmoothieApp extends JFrame{
	
	public SmoothieApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		//this.setLocation(400, 200);
		SmoothiePanel ts = new SmoothiePanel(this);
		this.add(ts);
		this.pack();
		this.setVisible(true);	
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		new SmoothieApp("Healthy Smoothie Bar App");
	}

}

