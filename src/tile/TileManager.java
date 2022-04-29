package tile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import main.*;
public class TileManager {
	
	GamePanel gp;
	public Tile[] tile; //LIST OF THE DIFFERENT TILES
	public int[][] mapTileNum; //MAPPING OF THE TILES ON THE SCREEN
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[32]; //THERE ARE 32 DIFFERENT TILES 
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //DEFINE THE 2D ARRAY WITH DIMENSION 100 X 100
		getTileImage(); //GET AND SAVE THE IMAGES OF THE TILES
		loadMap("/maps/advancedmap.txt"); //LOAD THE MAP FROM A TXT FILE
	}

	//FILL THE ARRRAY WITH TILES AND ITS IMAGES
	public void getTileImage() {
		
		try{
			setupImage(0, "water", false);
			setupImage(1, "waterVariant", true);
			setupImage(2, "stone", true);
			setupImage(3, "dirt", false);
			setupImage(4, "tree", true);
			setupImage(5, "sand", false);
			setupImage(6, "grass", false);
			setupImage(7, "grassVariant", false);
			setupImage(8, "grassCornerTopLeft", true);
			setupImage(9, "grassCornerTopRight", true);
			setupImage(10, "grassCornerBottomLeft", true);
			setupImage(11, "grassCornerBottomRight", true);
			setupImage(12, "grassJointTopLeft", true);
			setupImage(13, "grassJointTopRight", true);
			setupImage(14, "grassJointBottomRight", true);
			setupImage(15, "grassJointBottomLeft", true);
			setupImage(16, "grassMeetTop", true);
			setupImage(17, "grassMeetBottom", true);
			setupImage(18, "grassMeetRight", true);
			setupImage(19, "grassMeetLeft", true);
			setupImage(20, "sandCornerTopRight", false);
			setupImage(21, "sandCornerTopLeft", false);
			setupImage(22, "sandCornerBottomRight", false);
			setupImage(23, "sandCornerBottomLeft", false);
			setupImage(24, "sandMeetTop", false);
			setupImage(25, "sandMeetBottom", false);
			setupImage(26, "sandMeetRight", false);
			setupImage(27, "sandMeetLeft", false);
			setupImage(28, "sandJointTopRight", false);
			setupImage(29, "sandJointTopLeft", false);
			setupImage(30, "sandJointBottomRight", false);
			setupImage(31, "sandJOintBottomLeft", false);
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void loadMap(String filepath) {
		
		try {
			InputStream stream = getClass().getResourceAsStream(filepath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			
			int col = 0;
			int row = 0;
			
			// WHILE LOOP MAPS THE 2D ARRAY WITH CONTENTS OF THE TXT FILE
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) { // WHILE COL AND ROW LESS THAN 100 100 TILES
				
				String line = reader.readLine();
				System.out.println(line);//LOAD THE LINE
				
				//THIS WEIRD THING APPEARS ON THE TXT FILE, I JUST CUT IT
				if (line.contains("﻿")) {
					line = line.substring(3);
				}
				
				//WHILE COL LESS THAN 100
				while (col < gp.maxWorldCol) {
					
					String[] numbers = line.split(" ");//READ A SINGLE NUMBER 
					int num = Integer.parseInt(numbers[col]); //PARSE THE NUMBER TO INT
					
					mapTileNum[col][row] = num; //MAP THE NUMBER WHERE IT GOES
					col++; //INCREASE THE COLUMN
				}
				
				//ONCE THE COLUMN IS 100, COLUMN IS 0 AGAIN BUT WE MOVE TO THE NEXT ROW
				if (col == gp.maxWorldCol) {
						col = 0;
						row++;
				}
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("ERROR a: " + e.getLocalizedMessage());
		}
	}
	
	public void setupImage(int index, String imagePath, boolean collision) {
		ExtraTools tools = new ExtraTools();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = tools.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//DRAW METHOD FOR TILES
	public void draw(Graphics graph2D) {
		int col = 0;
		int row = 0;
		
		// WHILE COLS AND ROWS ARE LESS THAN 100
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[col][row]; // GET THE CURRENT NUMBER IN THE 2D ARRAY
			int worldX = col * gp.tileSize; // CALCULATE WHEN IN THE MAP IN PX ARE YOU GOING TO PLACE THE ARRAY
			int worldY = row * gp.tileSize; // SAME WITH Y
			int screenX = worldX - gp.player.playerX + gp.player.screenX; // WHERE IN THE CURRENT PART OF THE MAP THE SCREEN WILL DISPLAY THE TILES
			int screenY = worldY - gp.player.playerY + gp.player.screenY; // Y
			
			// DRAW THE TILES 16 AND 12 AROUND THE PLAYER ONLY
			if ((worldX + 16) + gp.tileSize> gp.player.playerX - gp.player.screenX &&
				(worldX - 16) - gp.tileSize < gp.player.playerX + gp.player.screenX &&
				(worldY + 16) + gp.tileSize > gp.player.playerY - gp.player.screenY &&
				(worldY - 16) - gp.tileSize < gp.player.playerY + gp.player.screenY) {
				graph2D.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			col++;
			
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
}
