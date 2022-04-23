package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class ParentObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int objectX, objectY;
	public Rectangle hitbox = new Rectangle(0,0,48,48);
	public int hitboxDefaultX = 0;
	public int hitboxDefaultY = 0;
	
	public void draw(Graphics graph2D, GamePanel gp) {
		int screenX = objectX - gp.player.playerX + gp.player.screenX;
		int screenY = objectY - gp.player.playerY + gp.player.screenY;
		
		if (objectX + gp.tileSize > gp.player.playerX - gp.player.screenX && 
			objectX - gp.tileSize < gp.player.playerX + gp.player.screenX && 
			objectY + gp.tileSize > gp.player.playerY - gp.player.screenY && 
			objectY - gp.tileSize < gp.player.playerY + gp.player.screenY) {
			graph2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

}
