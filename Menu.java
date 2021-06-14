package build_a_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import build_a_game.Game.STATE;

public class Menu extends MouseAdapter { 
	
	private Game game;
	private HUD hud;
	private Handler handler;
	private KeyInput keyInput;
	
	private Random r= new Random();
	
	public Menu(Game game, HUD hud, Handler handler, KeyInput keyInput) {
		this.game=game;
		this.hud=hud;
		this.handler=handler;
		this.keyInput=keyInput;
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 40);
		
		if (game.gameState==STATE.Menu) {		
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 50, 50);
		
			g.setFont(fnt2);
			g.setColor(Color.black);
			g.fillRect(220, 100, 220, 64);
			g.setColor(Color.white);
			g.drawRect(220, 100, 220, 64);
			g.drawString("PLAY", 290, 142);
			g.setColor(Color.black);
			g.fillRect(220, 200, 220, 64);
			g.setColor(Color.white);
			g.drawRect(220, 200, 220, 64);
			g.setColor(Color.black);
			g.fillRect(220, 300, 220, 64);
			g.setColor(Color.white);
			g.drawString("Help", 295, 242);
			g.drawRect(220, 300, 220, 64);
			g.drawString("EXIT", 300, 342);
		}
		
		if (game.gameState==STATE.Help) {
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Help", 270, 50);
			
			g.setFont(fnt2);
			g.drawString("This is you", 150, 150);
			g.fillRect(340, 125, 32, 32);
			g.drawString("You can move using the arrow keys", 50, 200);
			g.drawString("Try to dodge everything coming your way", 15, 250);
			g.drawRect(220, 320, 220, 64);
			g.drawString("Back", 290, 362);
						
		}
		
		if (game.gameState==STATE.Death) {
			g.setColor(Color.black);
			g.fillRect(64, 64, Game.WIDTH-128, Game.HEIGHT-128);
			g.setColor(Color.magenta);
			g.setFont(fnt);
			g.drawString("GAME OVER", 150, 150);
			g.setColor(Color.white);
			g.drawRect(180, 255, 265, 64);
			g.setFont(fnt2);
			g.drawString("Return to Menu", 200, 294);
			g.drawString("Your Score: " + hud.getScore(), 200, 184);
			
		}
		
		if (game.gameState==STATE.Victory) {
			g.setColor(Color.black);
			g.fillRect(64, 64, Game.WIDTH-128, Game.HEIGHT-128);
			g.setColor(Color.orange);
			g.setFont(fnt);
			g.drawString("First floor cleared!", 85, 150);
			g.setColor(Color.white);
			g.drawRect(180, 255, 265, 64);
			g.setFont(fnt2);
			g.drawString("Return to Menu", 200, 294);
			g.drawString("Your Score: " + hud.getScore(), 200, 184);
			
		}
	}
	
	public void tick() {
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if (game.gameState==STATE.Menu) {
			if (mouseOver(220, 100, 220, 64, e)) {
				game.gameState=STATE.Game;
				if (!handler.object.stream().anyMatch(i -> i.id==ID.Player)) {
					handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, game, handler, keyInput));
				}	
				handler.clearObjects();
			}
			if (mouseOver(220, 200, 220, 64, e)) {
				game.gameState=STATE.Help;
			}
			if (mouseOver(220, 300, 220, 64, e)) {
				System.exit(0);
			}
		}
		
		if (game.gameState==STATE.Help) {
			if (mouseOver(220,320,220,64,e)) {
				game.gameState=STATE.Menu;
			}
			
		}
		
		if (game.gameState==STATE.Death) {
			if (mouseOver(180, 255, 265, 64, e)) {
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
		
		if (game.gameState==STATE.Victory) {
			if (mouseOver(180, 255, 265, 64, e)) {
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
		
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int x, int y, int width, int heigth, MouseEvent e) {
		if ((x <=e.getX() && e.getX() <= x+width) && (y <= e.getY() && e.getY()<= y+ heigth)) {
			return true;
		}else {
			return false;
		}
	}
	
}
