package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjPop extends ParentObject{

	GamePanel gp;
	//POP IS AN OBJECTS
	public ObjPop(GamePanel gp){
		this.gp = gp;
		name = "pop";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/pop.png"));
			tools.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
