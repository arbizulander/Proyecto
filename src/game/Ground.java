package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Ground {

	int dx;
	int x,y,width,height;
	Image plat;
	URL url;
	float frame = 0;
	
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
		plat = Pictures.platform;
	}
	

public void update(StartingPoint sp, Jugador pr) {
	
		int tester = (int)(frame + .1);
		if (tester < 3) {
			frame += .1;
		}
		else {
			frame = 0;
		}
	
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
		
		//g.drawImage(plat, x, y, Pictures.sp);
		g.drawImage(plat, x, y, x + width, y + height, 0, 40*(int)frame,120, 40*(int)frame+40, Pictures.sp);
		//g.drawRect(x, y, width, height);
		
		//Dibujar icono usando se metodo paintIcon 
		//img.paintIcon(this, g, 180, 0); 
	}
	
}
