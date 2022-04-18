package tile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import main.*;
public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	int[][] mapTileNum;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/gamedatamap.txt");
	}

	public void getTileImage() {
		
		try{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[5] = new Tile();
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
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = reader.readLine();
				if (line.contains("﻿")) {
					line = line.substring(3);
				}
				while (col < gp.maxWorldCol) {
					
					String[] numbers = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
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
	public void draw(Graphics graph2D) {
		int col = 0;
		int row = 0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[col][row];
			int worldX = col * gp.tileSize;
			int worldY = row * gp.tileSize;
			double screenX = worldX - gp.player.playerX + gp.player.screenX;
			double screenY = worldY - gp.player.playerY + gp.player.screenY;
			
			if ((worldX + 16) + gp.tileSize> gp.player.playerX - gp.player.screenX && (worldX - 16) - gp.tileSize < gp.player.playerX + gp.player.screenX && (worldY + 16) + gp.tileSize > gp.player.playerY - gp.player.screenY && (worldY - 16) - gp.tileSize < gp.player.playerY + gp.player.screenY) {
				graph2D.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
			}
			col++;
			
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
}
