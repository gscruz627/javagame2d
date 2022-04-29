package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ExtraTools {

	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics graph2D = scaledImage.createGraphics();
		graph2D.drawImage(original, 0, 0, width, height, null);
		graph2D.dispose();
		return scaledImage;
	}
}
