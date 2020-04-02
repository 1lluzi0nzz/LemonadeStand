import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Engine implements Runnable{
	
	public JFrame frame;
	public Canvas canvas;
	public String title;
	public int width, height;
	
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
		new Engine("", 800, 600);
	}
	public void tick() {
		
	}
	public void render() {
	
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
		init();
	}

}
