import java.awt.Color;
import java.awt.Graphics;

public class MainMenuState extends State{

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.clearRect(0, 0, Engine.width, Engine.height);
		g.fillRect(0, 0, Engine.width, Engine.height);
		g.setColor(Color.yellow); 
		g.drawString("Lemonade Stand", 360, 30);
		g.fillRect(40, 150, 200, 20);
		g.fillRect(40, 250, 200, 20);
		g.fillRect(40, 350, 200, 20);
		g.setColor(Color.black);
		g.drawString("START", 120, 165);
		g.drawString("OPTIONS", 115, 265);
		g.drawString("EXIT", 125, 365);
		
		g.drawImage(Assets.lemon, 450, 100, null);
		g.drawImage(Assets.ice, 450, 300, null);
	}

}
