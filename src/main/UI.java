package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.ObjCard;

public class UI {

	GamePanel gp;
	Graphics2D graph2D;
	Font arial_40;
	Font arial_78;
	public String message = "";
	public boolean messageOn = false;
	public int messageCounter;
	public boolean gameFinish = false;
	
	public UI(GamePanel gp) {
	
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_78 = new Font("Arial", Font.BOLD, 78);
	}
	
	public void showMessage(String message) {
		this.message = message;
		messageOn = true;
	}
	public void draw(Graphics2D graph2D) {
		this.graph2D = graph2D;
		graph2D.setFont(arial_40);
		graph2D.setColor(Color.WHITE);
		
		//CHECK GAME STATE
		if (gp.gameState == gp.playState) {
		}
		else if (gp.gameState == gp.pauseState) {
			drawPausedScreen();
		}
	}
	public void drawPausedScreen() {
		String text = "P A U S E D";
		int x = calculateHorCenter(text);		
		int y =  gp.screenWidth / 2;
		graph2D.drawString(text, x, y);
	}
	public int calculateHorCenter(String text) {
		int lenght = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
		int x = gp.screenWidth/2 - lenght/2;
		return x;
	}
		
}
