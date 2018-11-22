package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ground {

	int dx;
	int x,y,width,height;
	
	public Ground () {
		dx = -5;
		x = 300;
		y = 300;
		width = 120;
		height = 40;
	}
	
	public Ground (int x, int y) {
		this.x = x;
		this.y = y;
		width = 120;
		height = 40;
		dx = - 5;
	}
	
public void update(StartingPoint sp, Jugador pr) {
	
		x += dx;
		checkCollision(pr);
		
		//movimiento de plataforma
		if (x < 0 - width) {
			Random r = new Random();
			y = sp.getHeight()-40 - r.nextInt(400);			
			x = sp.getWidth() + r.nextInt(300);
		}
	}
	
	private void checkCollision(Jugador pr) {
		
		//info jugador
		int posX = pr.getX();
		int posY = pr.getY();
		int radious = pr.getRadious();
		
		if (posY + radious > y && posY + radious < y + height) {
			if (posX > x && posX < x + width) {
				double newDY = pr.getDy()*-1;
				pr.setY(y-radious);
				pr.setDy(newDY);
			}			
		}
	}

	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		//g.drawRect(x, y, width, height);
	}
	
}
