package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class ParentObject {
	
	// EACH OBJECT HAS AN IMAGE, NAME, COLLISION STATE AND COORDINATES
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int objectX, objectY;
	
	//SET THE OBJECT'S HITBOX
	public Rectangle hitbox = new Rectangle(0,0,48,48);
	public int hitboxDefaultX = 0;
	public int hitboxDefaultY = 0;
	
	public void draw(Graphics graph2D, GamePanel gp) {
		
		//GET WHERE IN THE SCREEN ARE THE OBJECTS LOCATED
		int screenX = objectX - gp.player.playerX + gp.player.screenX;
		int screenY = objectY - gp.player.playerY + gp.player.screenY;
		
		// ONLY IF THE OBJECTS COORDINATES ARE ON THE RANGE OF THE PLAYER DRAW THEM
		if (objectX + gp.tileSize > gp.player.playerX - gp.player.screenX && 
			objectX - gp.tileSize < gp.player.playerX + gp.player.screenX && 
			objectY + gp.tileSize > gp.player.playerY - gp.player.screenY && 
			objectY - gp.tileSize < gp.player.playerY + gp.player.screenY) {
			graph2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

}
