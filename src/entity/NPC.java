package entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import main.ExtraTools;
import main.GamePanel;

public class NPC extends Entity{

	public NPC(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		
		getImage();
	}
	public void getImage() {
		
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
	}
	public void setAction() {
		actionCounter++;
		if (actionCounter > 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			if (i <= 25) {
				direction = "up";
			}
			else if (i > 25 && i <= 50) {
				direction = "down";
			}
			else if (i > 50 && i <= 75) {
				direction = "left";
			}
			else {
				direction = "right";
			}
			actionCounter = 0;
		}
	}
}
