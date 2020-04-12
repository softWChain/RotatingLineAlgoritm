package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 500;
	
	private Window window;
	private boolean running = false;
	private Thread thread;

	
	private Random rand = new Random();
	
	private int xMesafe,yMesafe;
	private double aci=0;
	private int uzunluk = 100;
	private int i,j = 0;
	
	public boolean right =true,left = false,up=false,down=false;
	
	public Game(){
		setFocusable(true);
		window = new Window(WIDTH,HEIGHT,this);
		
	}
	
	public void init(){
		setFocusable(true);
		
		xMesafe = 200;
		yMesafe = 200;
	
	}
	
	public void tick(){
		
		i = (int) ((int) uzunluk * Math.cos(aci));
		j = (int) ((int) uzunluk * Math.sin(aci));
		

		
		
		
		aci = aci + (0.05*Math.PI);

		
	}
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
		g.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
		
		g.drawLine(WIDTH/2, HEIGHT/2, WIDTH/2 + i, HEIGHT/2 - j);
		g.drawLine(WIDTH/2, HEIGHT/2, WIDTH/2 - i, HEIGHT/2 + j);

	
		
		bs.show();
		g.dispose();
		
	}
	
	public void run(){
		init();
		
		int FPS = 40;
		double targetFPS = 1000000000 / FPS;
		double delta = 0;
		
		long lastTime = System.nanoTime();
		long now;
		long timer = System.currentTimeMillis();
		
		int ticks=0;
		int updates = 0;
		
		while(running){
			
			now = System.nanoTime();
			delta +=(now - lastTime ) / targetFPS;
			lastTime = now;
			
			if(delta>=1){
				tick();
				ticks++;
				delta--;
			}
			render();
			updates++;
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				System.out.println("FPS : " + ticks + "  - UPDATES : " + updates);
				ticks = 0;
				updates = 0;
			}
			
			
		}
		
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this,"ThreadGame");
		thread.start();
		
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String args[]){
		new Game().start();
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	

}
