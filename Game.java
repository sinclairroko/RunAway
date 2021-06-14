package build_a_game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 3499358949212311826L;
	
	protected static final int WIDTH=640, HEIGHT=WIDTH/12*9;
	private boolean running=false;
	private Thread thread;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	public Spawn spawner;
	private KeyInput keyInput;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Help,
		Death,
		LevelUp,
		Victory,
		Game;
	};
	
	public STATE gameState=STATE.Menu;
	
	public Game() {
		this.handler= new Handler();
		this.r= new Random();
		this.hud = new HUD();
		this.keyInput= new KeyInput(this, handler, hud);
		this.addKeyListener(keyInput);
		new Window(WIDTH, HEIGHT, "Let's go!", this);
		this.menu= new Menu(this, hud, handler, keyInput);
		this.addMouseListener(menu);
		this.spawner=new Spawn(handler,hud);
		
		handler.addObject(new BackgroundObjectBasic(r.nextInt(WIDTH-96)+64, r.nextInt(HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
		handler.addObject(new BackgroundObjectBasic(r.nextInt(WIDTH-96)+64, r.nextInt(HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
		handler.addObject(new BackgroundObjectBasic(r.nextInt(WIDTH-96)+64, r.nextInt(HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
		handler.addObject(new BackgroundObjectFast(r.nextInt(WIDTH-96)+64, r.nextInt(HEIGHT-96)+64, ID.BackgroundObjectFast, handler));
}

	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch (Exception e) {
			e.printStackTrace();
		}
		thread.start();		
	}
	
	public void run() {
	    setFocusable(true);
	    requestFocus();
	    requestFocusInWindow();
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		long timer=System.currentTimeMillis();
		int frames=0;
		while(running) {
			long now=System.nanoTime();
			delta+= (now - lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer+=1000;
				System.out.println("FPS: " +  frames);
				frames=0;
			}
		}
		stop();
	}
	
	private void tick() {
		if (HUD.HEALTH<=0) {
			gameState=STATE.Death;
		}
		if (hud.getLevel()==11) {
			gameState=STATE.Victory;
		}
		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		}else if (gameState == STATE.Menu || gameState==STATE.Help || gameState==STATE.Death || gameState==STATE.Victory) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g=bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if (gameState==STATE.Menu || gameState==STATE.Help || gameState==STATE.Death || gameState==STATE.Victory){
			menu.render(g);
		}
		
		if (gameState==STATE.Game) {
			hud.render(g);
		}
		
		
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var < min) {
			return min;
		}
		if (var > max) {
			return max;
		}
		return var;
	}
	
		
	public static void main (String[] args) {
		new Game();
		
	}

}
