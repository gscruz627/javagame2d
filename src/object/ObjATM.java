package object;

import javax.imageio.ImageIO;

import main.GamePanel;

// ATM IS AN OBJECT
public class ObjATM extends ParentObject{

	GamePanel gp;
	
	public ObjATM(GamePanel gp) {
		this.gp = gp;
		name = "ATM";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/atm.png"));
			tools.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
