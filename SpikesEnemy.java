package build_a_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class SpikesEnemy extends GameObject {
	
	private Handler handler;
	private int timer=65;
	private boolean outwards=true;
	private int[] triangleX= new int[5];
	private int[] triangleY= new int[5];
	private Polygon p;

	public SpikesEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		int[] tXY = getTrianglePoints(x, y, 0, Game.WIDTH, 0, Game.HEIGHT, 10);
		System.arraycopy(tXY, 0, triangleX, 0, 5);
		System.arraycopy(tXY, 5, triangleY, 0, 5);
		this.p= new Polygon(triangleX, triangleY, 5);
		velX=0;
		velY=0;
	}
	
	public Rectangle getBounds() {
		return this.p.getBounds();
	}

	public void tick() {
		
		if (timer >=0 && outwards) {
			if (x==0) {
				velX=1;
			}else if(x==Game.WIDTH) {
				velX=-1;
			}else if(y==0) {
				velY=1;
			}else if(y==Game.HEIGHT) {
				velY=-1;
			}
			timer --;
		}else if(timer <0) {
			velX*=-1;
			velY*=-1;
			outwards=false;
			timer ++;
		}
		if (!outwards) {
			timer ++;
		}
		if (timer>=70 && !outwards) {
			outwards=true;
		}
		
		x+=velX;
		y+=velY;
				
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		int[] tXY = getTrianglePoints(x, y, 0, Game.WIDTH, 0, Game.HEIGHT, 10);
		System.arraycopy(tXY, 0, triangleX, 0, 5);
		System.arraycopy(tXY, 5, triangleY, 0, 5);
		this.p= new Polygon(triangleX, triangleY, 5);
		g.fillPolygon(p);
	}
	
	public int[] getTrianglePoints(int x, int y, int xmin, int xmax, int ymin, int ymax, int size) {
		int[] tP = new int[size];
		if (((x<= xmin+120) || (xmax-120 <= x)) && ((ymin+80<=y) && (y<= ymax-80))) {
			if (ymin <= y-10) {
				if (y+10 <= ymax) {
					tP[5]=y-10;
					tP[6]=y-5;
					tP[7]=y;
					tP[8]=y+5;
					tP[9]=y+10;
				}else {
					tP[5]=y-20;
					tP[6]=y-15;
					tP[7]=y-10;
					tP[8]=y-5;
					tP[9]=y;
				}
			}else {
				tP[5]=y;
				tP[6]=y+5;
				tP[7]=y+10;
				tP[8]=y+15;
				tP[9]=y+20;
			}
			if (x<=xmin+80) {
				tP[0]=x-80;
				tP[1]=x;
				tP[2]=x-80;
				tP[3]=x;
				tP[4]=x-80;
			}else {
				tP[0]=x+80;
				tP[1]=x;
				tP[2]=x+80;
				tP[3]=x;
				tP[4]=x+80;
			}
		}else {
			if (xmin <= x-10) {
				if (x+10 <= xmax) {
					tP[0]=x-10;
					tP[1]=x-5;
					tP[2]=x;
					tP[3]=x+5;
					tP[4]=x+10;
				}else {
					tP[0]=x-20;
					tP[1]=x-15;
					tP[2]=x-10;
					tP[3]=x-5;
					tP[4]=x;
				}
			}else {
				tP[0]=x;
				tP[1]=x+5;
				tP[2]=x+10;
				tP[3]=x+15;
				tP[4]=x+20;
			}
			if (y<=ymin+80) {
				tP[5]=y-80;
				tP[6]=y;
				tP[7]=y-80;
				tP[8]=y;
				tP[9]=y-80;
			}else {
				tP[5]=y+50;
				tP[6]=y-30;
				tP[7]=y+50;
				tP[8]=y-30;
				tP[9]=y+50;
			}
		}
		return tP;
	}
	
}


