package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class StartingPoint extends Applet implements Runnable{

	//Coordenadas
	int x = 400;
	int y = 25;
	int dx = 15;
	int dy = 0;
	int radious = 40;
	
	private Image i;
	private Graphics doubleG;

	//Fisicas
	double gravity = 15;
	double energyloss = .65;
	double xFriction = .9;
	double dt = .2;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		setSize (800,600);
	}
	
	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {	
		//Thread information
		while (true) {
			
			if (x+dx > this.getWidth()-radious) {
				x = this.getWidth()-radious-1;
				dx =- dx;
			}else if (x+dx < 0){
				x = 0;
				dx =- dx;
			}
			else {
				x += dx;
			}
			
			if (y == this.getHeight()-radious-1) {
				dx *= xFriction;
				if (Math.abs(dx) < .8) {
					dx = 0;
				}
			}
			
			if (y > this.getHeight()-radious-1) {
				y = this.getHeight() - radious -1;
				dy *= energyloss;
				dy = -dy;
			}
			else {
				//velocidad
				dy += gravity * dt;
				
				//posicion
				y += dy*dt + .5*gravity*dt*dt;
			}
			
			
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}	
	}
	
	@Override
	public void stop() {
		
	}

	@Override
	public void destroy() {
	
	}
	
	@Override
	//arreglo de parpadeo del objeto
	public void update (Graphics g){
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		
		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0,this.getSize().width, this.getSize().height);
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, radious, radious);
	}

	
}
