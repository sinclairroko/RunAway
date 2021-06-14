package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject {
	
	private Handler handler;
	private GameObject player;
	private Random r=new Random();

	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		for (int i=0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID()==ID.Player) {
				player=handler.object.get(i);
			}
		}
		velX=0;
		while (velX ==0) {
			velX=r.nextInt(6)-3;
		}
		velY=(int)Math.signum((double)player.getY()-y)*r.nextInt(4);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,12,12);
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
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 12, 12);
	}
	

}
