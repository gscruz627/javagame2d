package entity;
import java.awt.image.*;

public class Entity {
	public double playerX,playerY;
	public double speed;
	
	public BufferedImage  up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
