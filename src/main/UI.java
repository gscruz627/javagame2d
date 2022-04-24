package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.ObjCard;

public class UI {

	GamePanel gp;
	Font arial_40;
	Font arial_78;
	BufferedImage cardImage;
	public String message = "";
	public boolean messageOn = false;
	public int messageCounter;
	public boolean gameFinish = false;
	
	public UI(GamePanel gp) {
	
		this.gp = gp;
		arial_40 = new Font("Georgia", Font.PLAIN, 40);
		ObjCard card = new ObjCard();
		arial_78 = new Font("Arial", Font.BOLD, 78);
		cardImage = card.image;
	}
	
	public void showMessage(String message) {
		this.message = message;
		messageOn = true;
	}
	public void draw(Graphics2D graph2D) {
		if (gameFinish) {
			String text;
			int textLength;
			graph2D.setFont(arial_40);
			graph2D.setColor(Color.YELLOW);
			text = "You won";
			textLength = (int) graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
			
			int x = 768/2 - textLength/2;
			int y = 576/2 - gp.tileSize * 3;
			graph2D.drawString(text, x, y);
			
			graph2D.setFont(arial_78);
			graph2D.setColor(Color.DARK_GRAY);
			text = "Congrats";
			x = 768/2 - textLength/2;
			y = 576/2 - gp.tileSize *2;
			
			gp.gameThread = null;
			
		} else {
			graph2D.setFont(arial_40);
			graph2D.setColor(Color.WHITE);
			graph2D.drawImage(cardImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			graph2D.drawString("x " + gp.player.cards, 74, 60);
		
			//MESSAGES
			if (messageOn) {
				graph2D.setFont(graph2D.getFont().deriveFont(24F));
				graph2D.drawString(message, 0, 520);
				messageCounter++;
				
				if (messageCounter > 60) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
		
	}

}
