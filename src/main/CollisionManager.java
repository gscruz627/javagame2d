package main;
import entity.*;

public class CollisionManager {
	GamePanel gp;
	public CollisionManager(GamePanel gp) {
		this.gp = gp;
	}
	public void checkTile(Entity entity) {
		int entityLeftX = entity.playerX + entity.hitbox.x;
		int entityRightX = entity.playerX + entity.hitbox.x + entity.hitbox.width;
		int entityTopY = entity.playerY + entity.hitbox.y;
		int entityBottomY = entity.playerY + entity.hitbox.y + entity.hitbox.height;
		
		int entityLeftCol = entityLeftX / gp.tileSize;
		int entityRightCol = entityRightX / gp.tileSize;
		int entityTopRow = entityTopY / gp.tileSize;
		int entityBottomRow = entityBottomY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
			case "up":
				entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
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

}
