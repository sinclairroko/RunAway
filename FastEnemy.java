package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {
	
	private Handler handler;

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=2;
		velY=9;
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
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 12, 12);
	}
	

}
