import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage lemon,ice;
	
	public static void init() {
		lemon = Utilities.loadImage("lemon.png");
		ice = Utilities.loadImage("ice.jpg");
	}
}
