package main;

import entity.NPC;
import object.ObjATM;
import object.ObjCard;
import object.ObjDoor;
import object.ObjPop;

public class ObjectManager {
	GamePanel gp;
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
	}
	public void setNPC() {
		gp.npc[0] = new NPC(gp);
		gp.npc[0].playerX = gp.tileSize * 45;
		gp.npc[0].playerY = gp.tileSize * 49;
	}
}
