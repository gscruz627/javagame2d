package object;

import javax.imageio.ImageIO;

//DOOR IS AN OBJECT
public class ObjDoor extends ParentObject{

	public ObjDoor() {
		name = "door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		collision = true;
	}

}
