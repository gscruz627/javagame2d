package main;
import java.awt.event.*;

public class KeyHandler implements KeyListener{

	public boolean pressedUp, pressedDown, pressedLeft, pressedRight;
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
	}

	@Override
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
		if (code == KeyEvent.VK_ADD) {
			gp.zoom(1);
		}
		if (code == KeyEvent.VK_SUBTRACT) {
			gp.zoom(-1);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

}