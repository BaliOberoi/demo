/* 
 
 Final project created by Gabriela Farhat for the class IAT 265.
 The tree with recursion added is re-using the code of the project 3 I submitted for this class as well.
 
 This class contains all the major process of the application.

*/

package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Jug.Jug;
import processing.core.PVector;
import shopObjects.Bag;
import shopObjects.Berries;
import shopObjects.Greens;
import shopObjects.Fruits;
import shopObjects.ProgressBar;
import shopObjects.Blender;
import shopObjects.SmoothieShop;
import shopObjects.Protien;
import tree.Tree;
import util.ImageLoader;

public class SmoothiePanel extends JPanel implements ActionListener {
	
	//declaring the width and height of the panel
	public final static int SCREEN_W = 1080;
	public final static int SCREEN_H = 700;
	
	//declaring the TennisShop class for the background == shopBG done
	private SmoothieShop shopBackground;
	
	//declaring the StringMaching class for the string machine object == blender Machine done
	private Blender blenderMachine;
	
	//declaring the racquet class for the racquet object 
	private Jug jugs;
	
	//declaring the towel class for the towel object == Protien Powder
	private Protien protien;
	
	//declaring the ball class for the ball object == Berries
	private Berries berries;
	
	//declaring the towel class for the towel object == greens
	private Greens greens;
	
	//declaring the grip class for the grip object == Other Fruits
	private Fruits fruits;
	
	//declaring the bag class for the bag object
	private Bag bags;
	
	//private Glass glass;
	
	/*declaring the progress bar class to place the rectangle arraylist on on the progress 
	bar image that is in the background*/
	private ProgressBar progressBar;
	
	//declaring the mouse x and y for the user mouse clicks
	private double mouseX;
	private double mouseY;
	
	//declaring and initializing a boolean variable that checks when the user clicks on the steps in order to draw the progress bar rectangles
	boolean click1, click2, click3, click4, click5, click6, click7 = false;
	
	//declaring and initializing a boolean variable that checks when the bag of the background is being clicked by the user
	boolean circleClicked = false;
	
	//declaring and initializing a boolean variable that checks when the grey rectangle is being clicked by the user
	boolean rectClicked = false;
	
	//declaring and initializing a boolean variable that checks when the user drops the racquet on to the string machine
	boolean checkMouseReleased = false;
	
	//declaring and initializing a boolean variable that checks when the outline of the string machine is being clicked
	boolean checkMachineOutline = false;
	
	//declaring and initializing a boolean varibale that checks when the grip object is being clicked
	boolean fruitsClicked = false;
	
	//declaring and initializing a boolean variable that checks when the ball object is being clicked
	boolean berriesClicked = false;
	
	//declaring and initializing a boolean variable that checks when the towel object is being clicked
	boolean protienClicked = false;
	
	//declaring and initializing a boolean variable that checks when the dampener object is being clicked
	boolean greensClicked = false;
	
	boolean glassClicked = false;
	
	//declearing and initializing a boolean variable that checks when the state of the racquet is showing the racquet with no strings
	boolean newJug = false;
	
	//declaring and initializing a boolean variable that checks when the start rectangle on the image is being clicked by the user
	boolean startClicked = false;
	
	//declaring and initializing a boolean variable that checks when the go back rectangle on the image is being clicked by the user
	boolean goBackClicked = false;
	
	//declaring and initializing a boolean variable that checks when the instructions rectangle on the image is being clicked by the user
	boolean instructionsClicked = false;
	
	//declaring and initializing a boolean variable tha checks when the restart rectangle on the image is being clicked by the user
	boolean endClicked = false;
//	
//	private int jugState;
//	private int step1 = 0;
//	private int step2 = 1;
//	private int step3 = 2;
//	private int step4 = 3;
//	
	
	//declaring the timer
	private Timer timer;
	
	/* declaring and inializing the integer variables for my finite state machine which will check the flow of the game, 
	   when to show the start, instructions, start game, and game over parts of the game */
	private int STATE = 0;
	private int START = 0;
	private int INSTRUCTIONS = 1;
	private int PLAY = 2;
	private int GAMEOVER = 3;
	private int step1 = 4;
	private int step2 = 5;
	private int step3 = 6;
	private int step4 = 7;
	
	//declaring and intializing the start, game over,and instruction images
	private BufferedImage startImg;
	private BufferedImage endImg;
	private BufferedImage instuctionsImg;
	private BufferedImage glass;
	
	//initializing the arraylist for the recursion tree
	private ArrayList <Tree> branches;
	
	//initializing the JFrame to be able to restart the game. This means: go back to the start screen of the game
	private JFrame frame;
	
	public SmoothiePanel(JFrame frame) {
		setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
		this.frame = frame;
		
		//loading the images for the start, instructions and game over parts of the game
		shopBackground = new SmoothieShop("assets/shopBG.png", 115, 540);
		startImg = ImageLoader.loadImage("assets/start.png");
		endImg = ImageLoader.loadImage("assets/end screen.png");
		instuctionsImg = ImageLoader.loadImage("assets/INT.png");
		glass = ImageLoader.loadImage("assets/glass.png");
		
		//initializing the string machine object
		blenderMachine = new Blender(new PVector(650, 280));
		
		//initializing the towel object
		protien = new Protien(new PVector(116, 400));
		
		//initializing the ball object
		berries = new Berries(new PVector(116, 180));
		
		//initializing the dampener object
		greens = new Greens(new PVector(116, 325));
		
		//initializing the grip object
		fruits = new Fruits(new PVector(115, 250));
		
		//initializing the bag object
		bags = new Bag(new PVector());
		
		//initializing the racquet object
		jugs = new Jug(new PVector(450, 280));
		
		//initializing the progress bar object
		progressBar = new ProgressBar(new PVector(750, 350));
		
		
		
		////initializing the branches for the tree object
		branches = new ArrayList <Tree> ();
		
		//going through the arraylist for the branches of the tree and adding a tree object
		for(int i = 0; i < 2; i++)	{	
			
			branches.add(new Tree(1050, 280, 150, 7, 0, 5));
		}
		
		timer = new Timer(30, this);
		timer.start();
		
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		
		MyMouseMotionListener motionListener = new MyMouseMotionListener();
		this.addMouseMotionListener(motionListener);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//finite state machine to check the flow of the game
		//--------------------------------------------START,GOBACK,INSTRUCTIONS SCREENS---------------BELOW----------------------
		//when the state is in the start screen
		if (STATE == START) {
			//show the start screen image
			drawStartImg(g2);
			
			//if the start rectangle on the background is clicked, change the state to play the game
			if (startClicked == true) {
				STATE = PLAY;
			}
			
			//if the instructions rectangle on the background is clicked, change the state to instructions
			if(instructionsClicked == true) {
				STATE = INSTRUCTIONS;
			}
			
		//if the state is in instructions	
		} else if (STATE == INSTRUCTIONS) {
			//show the instructions image
			drawInstructionsImg(g2);
			
			//if the go back rectangle on the background is clicked, take that frame out and show the start image
			if (goBackClicked == true) {
				frame.dispose();
				frame = new SmoothieApp("Tennis Shop");
			}
			//--------------------------------------------START,GOBACK,INSTRUCTIONS SCREENS---------------ABOVE----------------------
			
		// if the state is in play game
		} else if (STATE == PLAY) {
			//draw the background of the tennis shop
			shopBackground.drawShop(g2);
			
			//draw the rectangle geom that will take the strings off the racquet
			shopBackground.drawRectangleButton(g2);
			
			//---------SUN COORDINATES IN SHOP CLASS
			//draw the sun geom----------SUN------
			shopBackground.drawSun(g2);
			
			//draw the grip object
			fruits.drawFruits(g2);
			
			//draw the towel object
			protien.drawProtien(g2);
			
			//draw the ball object
			berries.drawBerries(g2);
			
			//draw the dampener object
			greens.drawGreens(g2);
			
			//glass.drawGlass(g2);
			
			//Draw branches for the tree
			for(int i = 0; i < branches.size(); i++) {
				branches.get(i).drawTree(g2);
			}
			
		////-------------------------JUG AND MACHINE DROP CONNECTION BELOW----------------------
			/* if the user is releasing the racquet onto the string machine, 
			  draw the string machine that has the racquet on it */
			if(checkMouseReleased == true) {
				blenderMachine.setMachineState(true);
				blenderMachine.drawStringMachine(g2);
				
			/* if the user is not releasing the racquet onto the string machine, 
			  draw the string machine that does not have the racquet on it */
			} else if (checkMouseReleased == false) {
				blenderMachine.setMachineState(false);
				blenderMachine.drawStringMachine(g2);
			} 
		////-------------------------JUG AND MACHINE DROP CONNECTION ABOVE----------------------
			/* if the user clicks on the grip object, 
			   set the state of the racquet to be the image that has new grip, 
			   draw the racquet that has the new grip, and set the racquet2 
			   variable to false to make the user not click on the rectangle 
			   again which will show the racquet with no strings */
			
			
			/* if the user clicks on the string machine object, 
			   set the state of the racquet to be the image that has new strings, 
			   draw the racquet that has the new strings, and set the racquet2 
			   variable to false to make the user not click on the rectangle 
			   again which will show the racquet with no strings */
		 if (circleClicked == true) {
			jugs.setState(1);
			jugs.drawJug(g2);
			
			
			//-----STATE EMPTY------
			 }
		 if(rectClicked == true ) {
				// STATE = step1;
				jugs.setState(2);
				jugs.drawJug(g2);
				newJug = true;
				
				
			 }
		 if (berriesClicked == true ) {
				jugs.setState(3);
				jugs.drawJug(g2);
				//newJug = true;
				//STATE = step2;
					
			 } 
		 if (fruitsClicked == true ) {
					jugs.setState(4);
					jugs.drawJug(g2);
					//newJug = true;
					//STATE = step3;
					
			 }
		 if (greensClicked == true) {
					jugs.setState(5);
					jugs.drawJug(g2);
					//newJug = false;
			
			 } 
		 if (protienClicked == true) {
					jugs.setState(6);
					jugs.drawJug(g2);
					//newJug = false;
					
			 }
		 if(checkMachineOutline == true) {
				jugs.setState(7);
				jugs.drawJug(g2);
				
				
				STATE = GAMEOVER;
				
				
				
			} 
		 if(STATE == GAMEOVER) {
				//draw the game over image
				drawEndImg(g2);
				/* if the restart rectangle on the image is clicked by the user, 
				   get rid of that frame and add a new frame that contains the 
				   tennis app which restarts the game */
				if(endClicked == true) {
					frame.dispose();
					frame = new SmoothieApp("Tennis Shop");
				}
				
			}
			}
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public class MyMouseListener extends MouseAdapter {
		
		
		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			/* if the main state is in play game do the following. 
			   This if statement is placed here to make sure that the clicks 
			   dont happen when the start screen image is shown */
			if (STATE == PLAY) {
				
			////-------------------------INITIAL BAG BOOLEAN ----------------------
				//checks when the bag on the tennis shop background is clicked, which sets the boolean to true
				if (shopBackground.circleIsClicked(mouseX, mouseY)) {
					circleClicked = true;
				} 
				
				//checks when the rectangle geom is clicked, which sets the boolean to true
				if (shopBackground.rectButtonClicked(mouseX, mouseY)) {
					rectClicked = true;
					
					/* if the rectangle geom is not clicked yet, increase the progress bar 
					   and set the boolean to true to only draw the progress bar once */
					if(click1 == false) {
						progressBar.increaseProgressBar();
						click1 = true;
					}
				}
				
				
			////-------------------------MACHINE CLICKS----------------------
				/* checks when the string machine object is clicked, which sets the boolean to true and the 
				  mouse released boolean to false to make sure it only shows the image of the string machine 
				  that does not have the racquet in it */
				if(blenderMachine.outlineIsClicked(e, mouseX, mouseY) && newJug == true) {
					checkMachineOutline = true;
					checkMouseReleased = false;
					
					/* if the string machine object is not clicked yet, increase the progress bar 
					   and set the boolean to true to only draw the progress bar once */
					if(click2 == false) {
						progressBar.increaseProgressBar();
						click2 = true; 
					}
				}
			////-------------------------OTHER FRUITS CLICK BOOLEAN----------------------
				// checks when the grip object is clicked, which sets the boolean to true 
				if (fruits.clickedFruits(e, mouseX, mouseY) == true) {
					fruitsClicked = true;
					
					/* if the grip object is not clicked yet, increase the progress bar 
					   and set the boolean to true to only draw the progress bar once */
					if(click3 == false) {
						progressBar.increaseProgressBar();
						click3 = true;
					}
				}
			////-------------------------BERRIES CLICK BOOLEAN----------------------
				// checks when the ball object is clicked, which sets the boolean to true 
				if(berries.clickedBerries(e, mouseX, mouseY) == true) {
					berriesClicked = true;
					
					/* if the ball object is not clicked yet, increase the progress bar 
					   and set the boolean to true to only draw the progress bar once */
					if(click4 == false) {
						progressBar.increaseProgressBar();
						click4 = true;
					}
					
				}
			////-------------------------PROTIEN CLICK BOOLEAN----------------------
				// checks when the towel object is clicked, which sets the boolean to true 
				if(protien.clickedProtien(e, mouseX, mouseY) == true) {
					protienClicked = true;
					
					/* if the towel object is not clicked yet, increase the progress bar 
					   and set the boolean to true to only draw the progress bar once */
					if(click5 == false) {
						progressBar.increaseProgressBar();
						click5 = true;
					}
				}
			////------------------------- GREENS CLICK BOOLEAN----------------------
				// checks when the dampener object is clicked, which sets the boolean to true 
				if(greens.clickGreens(e, mouseX, mouseY) == true) {
					greensClicked = true;
					
					/* if the dampener object is not clicked yet, increase the progress bar 
					   and set the boolean to true to only draw the progress bar once  */
					if(click6 == false) {
						progressBar.increaseProgressBar();
						click6 = true;
					}
				}
				
			} 
		////-------------------------BUTTONS---------------START,GOBACK,INSTRUCTIONS, GAMEOVER---------------BELOW----------------------
			//if the start rectangle on the start image is clicked, set its boolean to true
			if(startButtonClicked(mouseX, mouseY)) {
				startClicked = true;
			}
			
			//if the restart rectangle on the game over image is clicked, set its boolean to true
			if(endButtonClicked(mouseX, mouseY)) {
				endClicked = true;
			}
			
			//if the go back rectangle on the instructions image is clicked, set its boolean to true
			if(goBackButtonClicked(mouseX, mouseY)) {
				goBackClicked = true;
			}
			
			//if the instruction rectangle on the start image is clicked, set its boolean to true
			if(instructionButtonClicked(mouseX, mouseY)) {
				instructionsClicked = true;
			}
		}
	////-------------------------BUTTONS---------------START,GOBACK,INSTRUCTIONS, GAMEOVER---------------ABOVE----------------------
		
		public void mouseReleased(MouseEvent e) {
			jugs.update();
			
		////---------------------------JUG COLLISION WITH BLENDER--------BELOW--------------
			//check if the string is colliding with the racquet and it is only colliding with the racquet that has no strings
		    if (blenderMachine.collision(jugs) == true && newJug == true ) {
		    	
		    	/* make the booleans for the mouseReleased true, the boolean for the rectangle geom 
		    	   that takes the strings off is false, and the bag on the shop background is false */
		    	checkMouseReleased = true;
		    	rectClicked = false;
		    	circleClicked = false;
		    	
		    	/* if the collision happens, increase the progress bar 
				   and set the boolean to true to only draw the progress bar once  */
		    	if(click7 == false) {
		    		progressBar.increaseProgressBar();
					click7 = true;
					
				}
		    }
		}
	}
	
	private class MyMouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			
			
		////---------------------------JUG DRAG CHECK METHOD---------------------- 
			//check if the user is clicking on the racquet, to be able to drag it out through the panel
			if (jugs.jugDragged(e)) {
				jugs.setLocation(new PVector(e.getX(), e.getY()));
			}
		}
    }
	
	
	////---------------------------DRAW-------------START,GOBACK,INSTRUCTIONS, GAMEOVERSCREENS---------------BELOW----------------------
	//draw the start screen image
	public void drawStartImg(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.scale(1, 1);
		g2.drawImage(startImg, 0,0,startImg.getWidth(), startImg.getHeight(), null);
		g2.setTransform(at);
	}
	
	//draw the game over image
	public void drawEndImg(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.scale(0.50, 0.50);
		g2.drawImage(endImg, 0, 0, endImg.getWidth(), endImg.getHeight(), null);
		g2.setTransform(at);
	}
	
	//draw the instructions image
	public void drawInstructionsImg(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.scale(1, 1);
		g2.drawImage(instuctionsImg, 0, 0, instuctionsImg.getWidth(), instuctionsImg.getHeight(), null);
		g2.setTransform(at);
	}
	
////---------------------------DRAW-------------START,GOBACK,INSTRUCTIONS, GAMEOVERSCREENS---------------ABOVE----------------------
	
	
////-------------------------BUTTONS---------------START,GOBACK,INSTRUCTIONS, GAMEOVER---------------BELOW----------------------
	//checks when the start rectangle on the start screen is clicked by the user
	public boolean startButtonClicked(double x, double y){
		boolean isClicked3 = false;
		if( x >= 227 && x <= ((double) 227 + (200)) &&
			y >= 444 && y <= ((double) 444 + (75))) {
			isClicked3 = true;
		}
		return isClicked3;
	}
	
	//checks when the restart rectangle on the game over screen is clicked by the user
	public boolean endButtonClicked(double x, double y){
		boolean isClicked4 = false;
		if( x >= 1260/2 - 106 && x <= ((double) 1260/2 - 106 + (251)) &&
			y >= 450 && y <= ((double) 450 + (73))) {
			isClicked4 = true;
		}
		
		return isClicked4;
	}
	
	//checks when the go back rectangle on the instructions screen is clicked by the user
	public boolean goBackButtonClicked(double x, double y){
		boolean isClicked5 = false;
		if( x >= 465 && x <= ((double) 465 + (201)) &&
			y >= 621 && y <= ((double) 621 + (75))) {
			isClicked5 = true;
		}
		return isClicked5;
	}

	//checks when the instructions rectangle on the start screen is clicked by the user
	public boolean instructionButtonClicked(double x, double y){
		boolean isClicked6 = false;
		if( x >= 564 && x <= ((double) 564 + (323)) &&
			y >= 444 && y <= ((double) 444 + (75))) {
			isClicked6 = true;
		}
		
		return isClicked6;
	}
////---------------------------BUTTON-------------START,GOBACK,INSTRUCTIONS, GAMEOVERSCREENS---------------ABOVE----------------------
}
