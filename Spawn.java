package build_a_game;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	
	private int scoreKeep=0;
	
	private Random r= new Random();
	private GameObject player;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler=handler;
		this.hud=hud;
		for (int i=0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID()==ID.Player) {
				this.player=handler.object.get(i);
			}
		}
	}
	
	public void tick() {
		scoreKeep++;
		
		if (scoreKeep >= 200) {
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1); 
			
			if (hud.getLevel()==2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64)+32, r.nextInt(Game.HEIGHT-64)+32, ID.BasicEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-64)+32, r.nextInt(Game.HEIGHT-64)+32, ID.SmartEnemy, handler));
			}
			if (hud.getLevel()==3) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-64)+32, r.nextInt(Game.HEIGHT-64)+32, ID.FastEnemy, handler));
			}
			if (hud.getLevel()>2 && scoreKeep%50==0) {
				for (int i=0; i < handler.object.size(); i++) {
					if (handler.object.get(i).getID()==ID.Player) {
						this.player=handler.object.get(i);
					}
				}
				int tx = Math.min(player.getX(), Game.WIDTH-player.getX());
				int ty = Math.min(player.getY(), Game.HEIGHT-player.getY());
				if (tx < ty) {
					if (player.getX()<=Game.WIDTH-player.getX()) {
						handler.addObject(new SpikesEnemy(0, player.getY(), ID.SpikesEnemy, handler));
					}else {
						handler.addObject(new SpikesEnemy(Game.WIDTH, player.getY(), ID.SpikesEnemy, handler));
					}	
				}else if(player.getY()<=Game.HEIGHT-player.getY()) {
					handler.addObject(new SpikesEnemy(player.getX(), 0, ID.SpikesEnemy, handler));
				}else {
					handler.addObject(new SpikesEnemy(player.getX(), Game.HEIGHT, ID.SpikesEnemy, handler));
				}
			}
			if (hud.getLevel()==5) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64)+32, r.nextInt(Game.HEIGHT-64)+32, ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-64)+32, r.nextInt(Game.HEIGHT-64)+32, ID.BasicEnemy, handler));		
			}
			if (hud.getLevel()==7) {
				handler.addObject(new BossEnemy(Game.WIDTH/2-48, -120, ID.BossEnemy, handler));
			}
			if (hud.getLevel()==10) {
				for (int i=0; i < handler.object.size(); i++) {
					if (handler.object.get(i).getID()==ID.BossEnemy) {
						handler.removeObject(handler.object.get(i));
					}
				}
			}
			if (hud.getLevel()==11) {
				handler.clearObjects();
			}
		}
		
	}

}
