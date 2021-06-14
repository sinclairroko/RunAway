package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {
	
	private Handler handler;
	private Random r= new Random();
	
	private int timer=80;

	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=0;
		velY=3;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,140,12);
	}

	public void tick() {
		
		if (timer == 0) {
			velY=0;
			while (velX==0) {
				velX=-2*(r.nextInt(2)-1);
			}
			timer--;
		}
		else if (timer >0){
			timer --;
		}
		
		if (x <= 25 || x>= Game.WIDTH - 175) {
			velX*=-1;
		}		
		
		x += velX;
		y += velY;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 140, 12, 0.1f, handler));
		
		if (timer <0 && timer%35==0) {
			for (int i=0; i < 20; i++) {
				handler.addObject(new BossEnemyBullet(x+r.nextInt(140), y, ID.BossEnemyBullet, handler));
			}
			timer--;
		}
		else if (timer <0 && timer%60==0) {
			for (int i=0; i< handler.object.size(); i++) {
				if (handler.object.get(i).getID()==ID.BossEnemyBullet) {
					handler.removeObject(handler.object.get(i));
				}
			}
			timer --;
		}
		else if (timer<0) {
			timer--;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 140, 12);
	}
	

}
