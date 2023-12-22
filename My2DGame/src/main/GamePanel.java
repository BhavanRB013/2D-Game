package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{//GamePanel works as a gamescreen && to run the Thread command
	//SCREEN SETTINGS
	final int originalTilesize = 16;//16x16 is be the default size of the character, NPCs and map tiles
	final int scale = 3;//as our screen is bigger for our purpose we can multiply with scale 16x3=48	
	
	public final int Tilesize = originalTilesize * scale;//48x48
	public final int maxScreencol = 16;//16 tile horizontally
	public final int maxScreenRow = 12;//12 tile vertically
	public final int ScreenWidth = Tilesize * maxScreencol;//768 pixels
	public final int ScreenHeight = Tilesize * maxScreenRow;//576 pixels
	
	//WORLD SETTING
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = Tilesize * maxWorldCol;
	public final int worldHeight = Tilesize * maxWorldRow;
	
	//FPS
	int fps = 60;
		
	TileManager tileM = new TileManager(this);
	KeyHandler KeyH = new KeyHandler();//Instantiating keyhandler
	Thread gameThread;//Once the thread start it keeps the program running until we stop
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssertSetter aSetter = new AssertSetter(this);
	public Player player = new Player(this,KeyH);
	public SuperObject obj[] = new SuperObject[10];
	
	//Creating CONSTRUCTOR
	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));//Set the size of the class(jpanel)
		this.setBackground(Color.black);//set the background color of the gamepanel to black
		this.setDoubleBuffered(true);//If set to true all the drawings from this component will be done in an offscreen painting buffer
		this.addKeyListener(KeyH);//GamePanel can recognize key input 
		this.setFocusable(true);//this way gamepanel can be focused to receive key inputs
	}
	
	public void setupGame() {
		aSetter.setObject();
	}
	
	public void startGameThread() {//new method
		gameThread = new Thread(this);// we are instantiating gameThread
		gameThread.start();
	}


	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/fps;//SLEEP METHOD
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			//long CurrentTime = System.nanoTime();//Returns the current value of the running JVM's high resolution time source, in nanoseconds
//			//System.out.println(CurrentTime);
//			
//			//1. UPDATE: update information such as character position
//			update();
//			//2. DRAW; draw the screen with updated information
//			repaint();//That's how we call paintComponent function 	
//			
//			try {
//				
//				double remainingTime = nextDrawTime - System.nanoTime();//for the next process to occur it is the required delay
//				remainingTime = remainingTime/1000000;//converting nano to milli as sleep() accepts only milli
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				
//				e.printStackTrace();
//			}
//		}			
//	}
	
	public void run() {
		
		//DELTA OR ACCUMULATOR METHOD
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				
				System.out.println("FPS:"+drawCount);
				timer = 0;
				drawCount = 0;
			}
			
		}
	}
	public void update() {
		
		player.update();

		
	}
	
	public void paintComponent(Graphics g) {//Graphics - A class that has many functions to draw objects on the screen
		super.paintComponent(g);//It is a format and super - to invoke functions from parent class
		Graphics2D g2 = (Graphics2D)g;//It extends the Graphics class to provide more sophisticated control over geometry, Coordinate transformation, color and text layout
		
		//TILE
		tileM.draw(g2);
		
		//OBJECT
		for(int i=0; i<obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		//PLAYER
		player.draw(g2);
		g2.dispose();
		
	}

}
