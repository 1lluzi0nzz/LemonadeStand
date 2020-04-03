import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Engine implements Runnable{
	
	public JFrame frame;
	public Canvas canvas;
	public String title;
	public int width, height;
	public BufferStrategy bs;
	public Graphics g;
	private Thread thread;
	public boolean running = false;
	
	public int x = 0;
	
	public Engine(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
		
	}
	public void init() {
		Assets.init();
	}
	public void tick() {
		x+= 3;
	}
	public void render() {
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.yellow); 
		g.drawString("Lemonade Stand", 360, 30);
		g.fillRect(40, 150, 200, 20);
		g.fillRect(40, 250, 200, 20);
		g.fillRect(40, 350, 200, 20);
		g.setColor(Color.black);
		g.drawString("START", 120, 165);
		g.drawString("OPTIONS", 115, 265);
		g.drawString("EXIT", 125, 365);
		
		g.drawImage(Assets.lemon, x, 100, null);
		g.drawImage(Assets.ice, 450, 300, null);
		
		
		
		bs.show();
		g.dispose();
	}
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				System.out.println("FPS: "+ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		if(!running) {
			return;
		}
		try {
			thread.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Engine eng = new Engine("", 800, 600);
		eng.start();
	}

}
