package tile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import main.*;
public class TileManager {
	
	GamePanel gp;
	public Tile[] tile; //LIST OF THE DIFFERENT TILES
	public int[][] mapTileNum; //MAPPING OF THE TILES ON THE SCREEN
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10]; //THERE ARE 10 DIFFERENT TILES 
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //DEFINE THE 2D ARRAY WITH DIMENSION 100 X 100
		getTileImage(); //GET AND SAVE THE IMAGES OF THE TILES
		loadMap("/maps/gamedatamap.txt"); //LOAD THE MAP FROM A TXT FILE
	}

	//FILL THE ARRRAY WITH TILES AND ITS IMAGES
	public void getTileImage() {
		
		try{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].collision = true;
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone.png"));
			
			tile[2] = new Tile();
			tile[2].collision = true;
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[5] = new Tile();
			tile[5].collision = true;
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
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
				
				String line = reader.readLine(); //LOAD THE LINE
				
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
			if ((worldX + 16) + gp.tileSize> gp.player.playerX - gp.player.screenX && (worldX - 16) - gp.tileSize < gp.player.playerX + gp.player.screenX && (worldY + 16) + gp.tileSize > gp.player.playerY - gp.player.screenY && (worldY - 16) - gp.tileSize < gp.player.playerY + gp.player.screenY) {
				graph2D.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			col++;
			
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
}
