package entity;
import java.awt.image.*;

import javax.imageio.ImageIO;

import main.ExtraTools;
import main.GamePanel;

import java.awt.geom.*;
import java.awt.*;

public class Entity {
	
	GamePanel gp;
	public int playerX,playerY;
	public int speed;
	
	public BufferedImage  up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	//Hit-box set up
	public Rectangle hitbox = new Rectangle(0,0,48,48);
	public int hitboxDefaultX, hitboxDefaultY;
	public boolean collisionOn = false;
	
	public int actionCounter;
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public BufferedImage setup(String imageName) {
		ExtraTools tools = new ExtraTools();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
			image = tools.scaleImage(image, gp.tileSize, gp.tileSize);
			return image;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public void draw(Graphics2D graph2D) {
		BufferedImage image = null;
		//GET WHERE IN THE SCREEN ARE THE OBJECTS LOCATED
		int screenX = playerX - gp.player.playerX + gp.player.screenX;
		int screenY = playerY - gp.player.playerY + gp.player.screenY;
		
		// ONLY IF THE OBJECTS COORDINATES ARE ON THE RANGE OF THE PLAYER DRAW THEM
		if (playerX + gp.tileSize > gp.player.playerX - gp.player.screenX && 
			playerX - gp.tileSize < gp.player.playerX + gp.player.screenX && 
			playerY + gp.tileSize > gp.player.playerY - gp.player.screenY && 
			playerY - gp.tileSize < gp.player.playerY + gp.player.screenY) {
			
			//SWITCH THE SPRITES
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
		}
	}
	public void setAction() {};
	public void update() {
		setAction();
		collisionOn = false;
		gp.collisionManager.checkTile(this);
		if (collisionOn == false) {
			switch(direction) {
				case "up": playerY -= speed; break;
				case "down": playerY += speed; break;
				case "left": playerX -= speed; break;
				case "right": playerX += speed; break;
			}
		}

		// REGARDLESS OF COLLISION THE PLAYER'S SPRITES WILL SWTICH (FROM UP1 TO UP2...)
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
