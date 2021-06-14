package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BackgroundObjectBasic extends GameObject {
	
	private Handler handler;
	private Random r = new Random();

	public BackgroundObjectBasic(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=0;
		velY=0;
		while(velX==0 || velY==0) {
		
			velX=r.nextInt(5);
			velY=r.nextInt(5);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

	public void tick() {
		if (y <= 0 || y>= Game.HEIGHT - 48) {
			velY*=-1;
		}
		
		if (x <= 0 || x>= Game.WIDTH - 32) {
			velX*=-1;
		}
		x += velX;
		y += velY;	
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}
	

}
