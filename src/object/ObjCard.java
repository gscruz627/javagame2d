package object;

import javax.imageio.ImageIO;

import main.GamePanel;

// CARD IS AN OBJECT
public class ObjCard extends ParentObject{

	GamePanel gp;
	public ObjCard(GamePanel gp) {
		this.gp = gp;
		name = "card";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/card.png"));
			tools.scaleImage(image, gp.tileSize, gp.tileSize); 
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
