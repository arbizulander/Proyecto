package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Coin {
	
	private int x, y, dx, radious;
	private StartingPoint sp;
	
	public Coin (int x) {
		this.x = x;
		Random r = new Random();
		y = r.nextInt(400)+radious;
		radious = 20;
		dx = -2;
		
	}

	public void update(StartingPoint sp, Jugador pr) {
		
		x += dx;
		this.sp = sp;
		checkCollision(pr);
		
		//movimiento de Moneda
		if (x < 0 - radious) {
			Random r = new Random();
			x = sp.getWidth() + 2000 + r.nextInt(300);
		}
	}
	
	private void checkCollision(Jugador pr) {
		
		//info jugador
		int posX = pr.getX();
		int posY = pr.getY();
		int pr_Radious = pr.getRadious();
		
		//calculo de distancia del punto central del jugador al punto central de la moneda
		int a = x - posX;
		int b = y - posY;
		int collide = radious + pr_Radious;
		double c = Math.sqrt((double)a*a + (double) b*b);
		
		if (c < collide) {
			performAction(pr);
			x = 0;
			y = sp.getHeight() + 100;
		}
	}

	public void performAction(Jugador pr) {
		// TODO Auto-generated method stub
		pr.setGravity(pr.getGravity()+3);
	}

	public void paint(Graphics g){
		//g.setColor(Color.BLACK);
		g.fillOval(x, y, radious, radious);
		//g.drawRect(x, y, width, height);
	}
	
	
	//Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDx() {
		return dx;
	}
	public int getRadious() {
		return radious;
	}
	public StartingPoint getSp() {
		return sp;
	}
	
	//Setters
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public void setRadious(int radious) {
		this.radious = radious;
	}	
	public void setSp(StartingPoint sp) {
		this.sp = sp;
	}
}
