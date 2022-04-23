package object;

import javax.imageio.ImageIO;

public class ObjATM extends ParentObject{

	public ObjATM() {
		name = "ATM";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/atm.png"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
