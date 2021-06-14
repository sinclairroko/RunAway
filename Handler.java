package build_a_game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object= new LinkedList<GameObject>();
	
	private GameObject player;
	
	public void tick() {
		for (int i=0; i < object.size(); i++) {
			GameObject tempObject=object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i=0; i< object.size(); i++) {
				GameObject tempObject=object.get(i);
			
				tempObject.render(g);
		}
	 }
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearObjects() {
		for (GameObject tempObject : this.object) {
			if (tempObject.getID()==ID.Player) {
				player=tempObject;
			}
		}
		this.object.clear();
		addObject(player);
	}
}
