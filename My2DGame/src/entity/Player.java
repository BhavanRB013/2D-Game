package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler KeyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler KeyH) {//CONSTRUCTOR
		this.gp = gp;
		this.KeyH = KeyH;
		
		screenX = gp.ScreenWidth/2 - (gp.Tilesize/2);
		screenY = gp.ScreenHeight/2 - (gp.Tilesize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.Tilesize * 23;
		worldY = gp.Tilesize * 21;
		speed = 4;
		direction = "up";
	}
	
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(KeyH.upPressed == true || KeyH.downPressed == true|| KeyH.leftPressed == true ||KeyH.rightPressed == true) {
			if(KeyH.upPressed == true) {
				direction = "up";
				
			}
			else if(KeyH.downPressed == true) {
				direction = "down";
				
			}
			else if(KeyH.leftPressed == true) {
				direction = "left";
				
			}
			else if(KeyH.rightPressed == true) {
				direction = "right";
				
			}
			
			//COLLISION CHECKING
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//IF COLLISION IS FALSE	
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			spriteCounter++;
			
			if(spriteCounter > 15) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum =1;  
				}
				spriteCounter = 0;
			}
		}	
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);//Sets a color to use for drawing objects
//		g2.fillRect(x, y, gp.Tilesize, gp.Tilesize);//Draw a rectangel and fills it with the specified color
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.Tilesize, gp.Tilesize, null);// Draw the image on the screen
		 
	}
}















