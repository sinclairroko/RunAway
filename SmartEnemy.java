package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		for (int i=0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player) {
				player=handler.object.get(i);
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,48,48);
	}

	public void tick() {
		double diffX=player.getX()-x;
		double diffY=player.getY()-y;
		double distance=Math.sqrt(diffX*diffX+diffY*diffY);
		
		velX=(int) (2.0/distance*diffX);
		velY=(int)(2.0/distance*diffY);
		
		x += velX;
		y += velY;	
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 48, 48, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 48, 48);
	}
	

}
