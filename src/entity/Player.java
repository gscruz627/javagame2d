package entity;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler kh;
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler kh){
		
		this.gp = gp;
		this.kh = kh;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		playerX = gp.tileSize * 61;
		playerY = gp.tileSize * 82;
		speed = gp.worldWidth / (gp.worldWidth / 4);
		direction = "down";
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
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void update() {
    	if (kh.pressedUp) {
    		playerY -= speed;
    		direction = "up";
    	}
    	
    	else if (kh.pressedLeft) {
    		playerX -= speed;
    		direction = "left";
    	}
    	
    	else if (kh.pressedDown) {
    		playerY += speed;
    		direction = "down";
    	}
    	
    	else if (kh.pressedRight) {
    		playerX += speed;
    		direction = "right";
    	}
    	
    	if (kh.pressedDown || kh.pressedUp || kh.pressedLeft || kh.pressedRight) {
    	spriteCounter++;
    	if (spriteCounter > 5) {
    		if (spriteNum == 1) {
    			spriteNum = 2;
    		}
    		else if (spriteNum == 2) {
    			spriteNum = 1;
    		}
    		spriteCounter = 0;
    	}
    	}
	}
	public void draw(Graphics graph2D) {
    	
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if (spriteNum  == 1) {
				image = up1;
			}
			else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum  == 1) {
				image = down1;
			}
			else if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum  == 1) {
				image = left1;
			}
			else if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum  == 1) {
				image = right1;
			}
			else if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		graph2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    	graph2D.dispose();
	}
}
