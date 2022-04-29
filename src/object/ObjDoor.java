package object;

import javax.imageio.ImageIO;

import main.GamePanel;

//DOOR IS AN OBJECT
public class ObjDoor extends ParentObject{

	GamePanel gp;
	public ObjDoor(GamePanel gp) {
		this.gp = gp;
		name = "door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			tools.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		collision = true;
	}

}
