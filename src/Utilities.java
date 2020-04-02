import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Utilities {
	
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Utilities.class.getResource(path));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
