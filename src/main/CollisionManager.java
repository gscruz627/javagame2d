package main;
import entity.*;

public class CollisionManager {
	
	// GATHER THE PANEL
	GamePanel gp;
	public CollisionManager(GamePanel gp) {
		this.gp = gp;
	}
	
	// CHECK THE TILES FOR COLLISION
	public void checkTile(Entity entity) {
		
		// SET AN ENTITY'S 'LIMITS' (LIKE A WALL ON THEIR RIGHT LEFT TOP AND BOTTOM
		int entityLeftX = entity.playerX + entity.hitbox.x;
		int entityRightX = entity.playerX + entity.hitbox.x + entity.hitbox.width;
		int entityTopY = entity.playerY + entity.hitbox.y;
		int entityBottomY = entity.playerY + entity.hitbox.y + entity.hitbox.height;
		
		// BASED ON THAT DETERMINE THE COLUMNS AND ROWS THAT THE PLAYER IS AROUND
		int entityLeftCol = entityLeftX / gp.tileSize;
		int entityRightCol = entityRightX / gp.tileSize;
		int entityTopRow = entityTopY / gp.tileSize;
		int entityBottomRow = entityBottomY / gp.tileSize;
		
		// DECLARE THE TWO TILES FACING THE SIDE OF PLAYER
		int tileNum1, tileNum2;
		
		// CHECK THE DIRECTION OF THE PLAYER, IF FACING UP, TILES ARE THE TWO IN THE TOP OF PLAYER
		switch(entity.direction) {
			case "up":
				entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
				//GET THE TILE
				tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
				//ONCE THE TILES HAVE BEEN FOUND CHECK FOR COLLISION AND CHANGE THE PLAYER'S COLLISION STATE IF NEEDED
				if (gp.tileManager.tile[tileNum1].collision == true|| gp.tileManager.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "down": 
				entityBottomRow = ((entityBottomY - entity.speed + 8) / gp.tileSize); 
				tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
				if (gp.tileManager.tile[tileNum1].collision == true|| gp.tileManager.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "left": 
				entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize; 
				tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
				if (gp.tileManager.tile[tileNum1].collision == true|| gp.tileManager.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "right": 
				entityRightCol = (entityRightX - entity.speed + 8) / gp.tileSize; 
				tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
				if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
		}
	}
	
	//CHECK FOR COLLISION OF OBJECTS
	public int checkObject(Entity entity, boolean player) {
		
		int index = -1;
		
		//FOR ALL OBJECTS
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				// GET THE PLAYER'S HITBOX
				entity.hitbox.x = entity.playerX + entity.hitbox.x;
				entity.hitbox.y = entity.playerY + entity.hitbox.y;
				// GET THE OBJECT'S HITBOX
				gp.obj[i].hitbox.x = gp.obj[i].objectX + gp.obj[i].hitbox.x;
				gp.obj[i].hitbox.y = gp.obj[i].objectY + gp.obj[i].hitbox.y;
				
				// BASED ON THE DIRECTION, 
				switch(entity.direction) {
				case "up":
					// CHECK THAT IF THE PLAYER MOVES AND THE HITBOX OF BOTH INTERSECT CHANGE COLLISION STATE AND INDEX OF COLLISION
					entity.hitbox.y -= entity.speed;
					if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "down":
					entity.hitbox.y += entity.speed;
					if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "right":
					entity.hitbox.x += entity.speed;
					if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "left":
					entity.hitbox.x -= entity.speed;
					if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				}
				
				// ONCE DOING THE CALCULATIONS FOR INTERSECTIONS, SET THE HITBOXES TO DEFAULT
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				gp.obj[i].hitbox.x = gp.obj[i].hitboxDefaultX;
				gp.obj[i].hitbox.y = gp.obj[i].hitboxDefaultY;
			}
		}
		
		// RETURN THE INDEX OF THE OBJECT (IF NO OBJECT, RETURNS -1)
		return index;
		
	}
	public int checkEntity(Entity entity, Entity[] target) {
int index = -1;
		
		//FOR ALL OBJECTS
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				// GET THE PLAYER'S HITBOX
				entity.hitbox.x = entity.playerX + entity.hitbox.x;
				entity.hitbox.y = entity.playerY + entity.hitbox.y;
				// GET THE OBJECT'S HITBOX
				target[i].hitbox.x = target[i].playerX + target[i].hitbox.x;
				target[i].hitbox.y = target[i].playerY + target[i].hitbox.y;
				
				// BASED ON THE DIRECTION, 
				switch(entity.direction) {
				case "up":
					// CHECK THAT IF THE PLAYER MOVES AND THE HITBOX OF BOTH INTERSECT CHANGE COLLISION STATE AND INDEX OF COLLISION
					entity.hitbox.y -= entity.speed;
					if (entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
					}
					break;
				case "down":
					entity.hitbox.y += entity.speed;
					if (entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
					}
					break;
				case "right":
					entity.hitbox.x += entity.speed;
					if (entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
					}
					break;
				case "left":
					entity.hitbox.x -= entity.speed;
					if (entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
					}
					break;
				}
				
				// ONCE DOING THE CALCULATIONS FOR INTERSECTIONS, SET THE HITBOXES TO DEFAULT
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				target[i].hitbox.x = target[i].hitboxDefaultX;
				target[i].hitbox.y = target[i].hitboxDefaultY;
			}
		}
		
		// RETURN THE INDEX OF THE OBJECT (IF NO OBJECT, RETURNS -1)
		return index;
		

	}
}
