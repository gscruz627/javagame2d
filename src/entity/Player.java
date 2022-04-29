package entity;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import main.*;

//PLAYER IS AN ENTITY
public class Player extends Entity{
	
	//GET THE PANEL AND THE KEYBOARD HANDLER
	KeyHandler kh;
	
	//PIXEL LIMITS TO DISPLAY ON THE SCREEN  (AROUND PLAYER)
	public final int screenX;
	public final int screenY;
	
	//OBJECTS PICKED UP (INVENTORY)
	
	//HELP VARIABLES
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler kh){
		super(gp);
		this.kh = kh;
		
		//SCREEN DISPLAY AROUND PLAYER IS IN THE MIDDLE (PLAYER WILL THE DISPLAYED IN THE MIDDLE (OF THE SCREEN))
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
		
		//CREATE THE PLAYER HITBOX (UNSTABLE)
		hitbox = new Rectangle(8,16,32,32);
		
		//BECAUSE THE HITBOX MAY CHANGE, CREATE DEFAULT VALUES
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		
	}
	
	public void setDefaultValues() {
		
		//PLAYER'S LOCATION IN THE MAP IS 61,82 (IN TILES)
		playerX = gp.tileSize * 45;
		playerY = gp.tileSize * 47;
		
		//PLAYER'S SPEED IS 4PX PER MOVEMENT, AND IS FACING DOWN BY DEFAULT
		speed = 4;
		direction = "down";
		
	}
	
	//LOAD THE IMAGES OF PLAYER, UP, DOWN, ETC...
	public void getPlayerImage() {
	
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
	}
	
	//UPDATE THE PLAYER'S DIRECTION AND MOVEMENT
	public void update() {
		
		//IF ANY KEY IS PRESSED
    	if (kh.pressedDown || kh.pressedUp || kh.pressedLeft || kh.pressedRight) {
    		
    		// IF THIS KEY IS PRESSED
    		if (kh.pressedUp) {
    			direction = "up";
    		}
    	
    		else if (kh.pressedLeft) {
    			direction = "left";
    		}
    	
    		else if (kh.pressedDown) {
    			direction = "down";
    		}
    	
    		else if (kh.pressedRight) {
    			direction = "right";
    		}
    	
    		// CHECKING COLLISION ON EACH MOVEMENT
    		collisionOn = false;
    		gp.collisionManager.checkTile(this);
    	
    		// CHECKING COLLISION FOR OBJECTS
    		int objIndex = gp.collisionManager.checkObject(this, true);
    		resolveObjectCollision(objIndex);
    	
    		int npcIndex = gp.collisionManager.checkEntity(this, gp.npc);
    		resolveNPCCollision(npcIndex);
    		// ONLY IF NO COLLISION IS DETECTED WILL THE PLAYER ACTUALLY MOVE
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
    	else {
    		standCounter++;
    		if (standCounter > 20) {
    			spriteNum = 1;
    			standCounter = 0;
    		}
    	}
	}
	
	// ESTABLISHED WHAT INTERACTION HAPPENS WITH AN OBJECT IF IT COLLIDES WITH PLAYER
	public void resolveObjectCollision(int i) {
		
		// IF THE PLAYER COLLIDED WITH AN OBJECT
		if (i != -1) {
			
		}
	}
	public void resolveNPCCollision(int i) {
		if (i != -1) {
			System.out.println("helo");
		}
	}
	
	//DRAW THE PLAYER
	public void draw(Graphics graph2D) {
    	
		BufferedImage image = null;
		
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
		
		// WHEN THE SPRITE IS LOADED AND EVERYTHING IS UPDATED, ACTUALLY DRAW THE PLAYER IN THE MIDDLE OF THE SCREEN
		graph2D.drawImage(image, screenX, screenY, null);
	}
}
