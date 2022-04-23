package object;

import javax.imageio.ImageIO;

public class ObjCard extends ParentObject{

	public ObjCard() {
		name = "card";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/card.png"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
