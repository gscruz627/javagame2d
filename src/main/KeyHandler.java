package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener{

	//ADD KEY STATES
	public boolean pressedUp, pressedDown, pressedLeft, pressedRight, zoomedIn;
	GamePanel gp;
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			pressedUp = true;
		}
		
		else if (code == KeyEvent.VK_LEFT) {
			pressedLeft = true;
		}
		
		else if (code == KeyEvent.VK_DOWN) {
			pressedDown = true;
		}
		
		else if (code == KeyEvent.VK_RIGHT) {
			pressedRight = true;
		}
		
		/*else if (code == KeyEvent.VK_Z) {
			gp.zoomIn();
		}*/
	
	}

	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			pressedUp = false;
		}
		
		if (code == KeyEvent.VK_LEFT) {
			pressedLeft = false;
		}
		
		if (code == KeyEvent.VK_DOWN) {
			pressedDown = false;
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			pressedRight = false;
		}
		
		/*if (code == KeyEvent.VK_Z) {
			System.out.println("received Z");
			gp.zoomOut();
		}*/
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	// KEY HANDLER CONSTRUCTOR GETS THE PANEL
	public KeyHandler(GamePanel gp) {
		
		this.gp = gp;
	
	}

}
