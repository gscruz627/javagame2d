package object;

import javax.imageio.ImageIO;

public class ObjPop extends ParentObject{

	//POP IS AN OBJECTS
	public ObjPop(){
		name = "pop";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/pop.png"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
