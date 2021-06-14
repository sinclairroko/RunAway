package build_a_game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH=100;
	private int greenValue=255;
	
	private int score=0;
	private int level=1;
	
	public void tick() {
		HEALTH=Game.clamp(HEALTH, 0, 100);
		greenValue=Game.clamp(greenValue,0,255);
		score++;
		greenValue=HEALTH*2;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 26);		
		g.setColor(new Color(125, greenValue, 0));
		g.fillRect(15, 15, HEALTH*2, 26);
		g.setColor(Color.white);
		g.drawRect(15, 15, HEALTH*2, 26);
		
		g.drawString("Score: " + score, 15, 56);
		g.drawString("Level: " + level, 15, 70);
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score=score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level=level;
	}
}
