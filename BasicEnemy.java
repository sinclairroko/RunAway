package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {
	
	private Handler handler;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=5;
		velY=5;
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
