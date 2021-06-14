package build_a_game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import build_a_game.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private boolean[] keyDown= new boolean[] {false, false, false, false};
	
	private Random r= new Random();
	
	public KeyInput(Game game, Handler handler, HUD hud) {
		this.handler=handler;
		this.game=game;
		this.hud=hud;
		
	}
		
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject=handler.object.get(i);
			
			if (tempObject.getID()==ID.Player) {
				if (key == KeyEvent.VK_UP) {
					//tempObject.setVelY(tempObject.getVelY()-5);
					keyDown[0]=true;
				}
				if (key == KeyEvent.VK_DOWN) {
					//tempObject.setVelY(tempObject.getVelY()+5);
					keyDown[1]=true;
				}
				if (key == KeyEvent.VK_LEFT) {
					//tempObject.setVelX(tempObject.getVelX()-5);
					keyDown[2]=true;
				}
				if (key == KeyEvent.VK_RIGHT) {
					//tempObject.setVelX(tempObject.getVelX()+5);
					keyDown[3]=true;
				}	
			}
		}
		if (key==KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		if (game.gameState==STATE.Menu && key==KeyEvent.VK_ENTER) {
			game.gameState=STATE.Game;
			if (!handler.object.stream().anyMatch(i -> i.id==ID.Player)) {
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, game, handler, this));
			}
			handler.clearObjects();
		}
		
		if (game.gameState==STATE.Death && key==KeyEvent.VK_ENTER) {
			game.gameState=STATE.Menu;
			handler.object.clear();
			HUD.HEALTH=100;
			hud.setScore(0);
			hud.setLevel(1);
			handler.addObject(new BackgroundObjectBasic(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
			handler.addObject(new BackgroundObjectBasic(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
			handler.addObject(new BackgroundObjectBasic(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
			handler.addObject(new BackgroundObjectFast(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectFast, handler));
		}
		
		if (game.gameState==STATE.Victory && key==KeyEvent.VK_ENTER) {
			game.gameState=STATE.Menu;
			handler.object.clear();
			HUD.HEALTH=100;
			hud.setScore(0);
			hud.setLevel(1);
			handler.addObject(new BackgroundObjectBasic(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
			handler.addObject(new BackgroundObjectBasic(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
			handler.addObject(new BackgroundObjectBasic(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectBasic, handler));
			handler.addObject(new BackgroundObjectFast(r.nextInt(Game.WIDTH-96)+64, r.nextInt(Game.HEIGHT-96)+64, ID.BackgroundObjectFast, handler));
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key= e.getKeyCode();
		
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject=handler.object.get(i);
			
			if (tempObject.getID()==ID.Player) {
				if (key == KeyEvent.VK_UP) {
					keyDown[0]=false;					
				}
				if (key == KeyEvent.VK_DOWN) {
					keyDown[1]=false;					
				}
				if (key == KeyEvent.VK_LEFT) {
					keyDown[2]=false;					
				}
				if (key == KeyEvent.VK_RIGHT) {
					keyDown[3]=false;					
				}
			}
		}
	}
	
	public boolean getKeyDownElement(int i) {
		return keyDown[i];
	}
	
}
