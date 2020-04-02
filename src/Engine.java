import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

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
	
	//Engine class creates window
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
	public static void init() {
		//new Engine(title, width, height);
	}
	public void tick() {
		
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
		g.drawImage(new Image("res/logo.png"), 0, 0);
		
		
		g.drawString("Lemonade Stand", 360, 30);
		bs.show();
		g.dispose();
	}
	public void run() {
		init();
		
		while(running) {
			tick();
			render();
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
