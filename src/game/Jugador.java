package game;

import java.awt.Color;
import java.awt.Graphics;


public class Jugador {
	
	//Coordenadas
	private int x = 400;
	private int y = 25;
	private double dx = 0;
	private double dy = 0;
	private int radious = 40;

	//Fisicas
	private double gravity = 15;
	private double energyloss = .65;
	private double xFriction = .9;
	private double dt = .2;
	private double gameDy = -75;
	
	private int agility = 1;
	private int maxSpeed = 20;
	

	public Jugador () {
		
	}
	
	public Jugador (int Ex, int Iy) {
		this.x = Ex;
		this.y = Iy;
	}
	
	//movimiento a la derecha
	public void moveRight() {
		if (dx + agility < maxSpeed) {
			dx+=agility;
		}
	}
	
	//movimiento a la izquierda
	public void moveLeft() {
		if (dx - agility > - maxSpeed) {
			dx-=agility;
		}
	}
	
	//movimiento hacia arriba
	public void moveUp() {
		if (dy < radious+1) {
			dy-=30;
		}
	}
	
	public void update(StartingPoint sp) {
		
		if (x+dx > sp.getWidth()-radious) {
			x = sp.getWidth()-radious-1;
			dx =- dx;
		}else if (x+dx < 0){
			x = 0;
			dx =- dx;
		}
		else {
			x += dx;
		}
		
		if (y == sp.getHeight()-radious-1) {
			dx *= xFriction;
			if (Math.abs(dx) < .8) {
				dx = 0;
			}
		}
		
		if (y > sp.getHeight()-radious-1) {
			y = sp.getHeight() - radious -1;
			dy *= energyloss;
			dy = gameDy;
		}
		else {
			//velocidad
			dy += gravity * dt;
			
			//posicion
			y += dy*dt + .5*gravity*dt*dt;
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval(x, y, radious, radious);
	}
	
	//Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public double getDx() {
		return dx;
	}
	public double getDy() {
		return dy;
	}
	public int getRadious() {
		return radious;
	}
	public double getGravity() {
		return gravity;
	}
	public double getEnergyloss() {
		return energyloss;
	}
	public double getxFriction() {
		return xFriction;
	}
	public double getDt() {
		return dt;
	}
	public double getGameDy() {
		return gameDy;	
	}
	public int getAgility() {
		return agility;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}

	//Setters
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}	
	public void setDy(double dy) {
		this.dy = dy;
	}
	public void setRadious(int radious) {
		this.radious = radious;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	public void setEnergyloss(double energyloss) {
		this.energyloss = energyloss;
	}
	public void setxFriction(double xFriction) {
		this.xFriction = xFriction;
	}
	public void setDt(double dt) {
		this.dt = dt;
	}
	public void setGameDy (double gameDy) {
		this.gameDy = gameDy;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
}
