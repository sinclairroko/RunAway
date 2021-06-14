package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import build_a_game.Game.STATE;

public class Player extends GameObject {
	
	private Game game;
	private Handler handler;
	private KeyInput keyInput;
	
	public Player(int x, int y, ID id, Game game, Handler handler, KeyInput keyInput) {
		super(x,y,id);
		this.game=game;
		this.handler=handler;
		this.keyInput=keyInput;

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

	public void tick() {
		if (keyInput.getKeyDownElement(0)) {
			velY-=1;
		}
		if (keyInput.getKeyDownElement(1)) {
			velY+=1;
		}
		if (keyInput.getKeyDownElement(2)) {
			velX-=1;
		}
		if (keyInput.getKeyDownElement(3)) {
			velX+=1;
		}
		if ((keyInput.getKeyDownElement(0) && keyInput.getKeyDownElement(1)) || (!keyInput.getKeyDownElement(0) && !keyInput.getKeyDownElement(1))) {
			velY=0;
		}
		if (keyInput.getKeyDownElement(2) && keyInput.getKeyDownElement(3) || (!keyInput.getKeyDownElement(2) && !keyInput.getKeyDownElement(3))) {
			velX=0;
		}
		if (game.gameState==STATE.Death) {
			velX=0;
			velY=0;
		}
		
		x+=velX;
		y+=velY;
		
		x=Game.clamp(x, 0, Game.WIDTH-46);
		y=Game.clamp(y, 0, Game.HEIGHT-70);
		
		collision();
	}
	
	public void collision() {
		for (int i=0; i< handler.object.size(); i++) {
			GameObject tempObject=handler.object.get(i);
			
			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID()==ID.SmartEnemy || tempObject.getID()==ID.FastEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=2;
				}
			}
			if (tempObject.getID() == ID.BossEnemyBullet) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=1;
				}
			}
			if (tempObject.getID() == ID.BossEnemy || tempObject.getID()==ID.SpikesEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=10;
				}
			}	
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);	
		
	}

}
