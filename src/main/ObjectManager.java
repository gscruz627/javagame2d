package main;

import object.ObjATM;
import object.ObjCard;
import object.ObjDoor;

public class ObjectManager {
	GamePanel gp;
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new ObjCard();
		gp.obj[0].objectX = 62 * gp.tileSize;
		gp.obj[0].objectY = 83 * gp.tileSize;
		
		gp.obj[1] = new ObjCard();
		gp.obj[1].objectX = 61 * gp.tileSize;
		gp.obj[1].objectY = 72 * gp.tileSize;
		
		gp.obj[2] = new ObjATM();
		gp.obj[2].objectX = 30 * gp.tileSize;
		gp.obj[2].objectY = 70 * gp.tileSize;
		
		gp.obj[3] = new ObjDoor();
		gp.obj[3].objectX = 61 * gp.tileSize;
		gp.obj[3].objectY = 78 * gp.tileSize;
	}
}
