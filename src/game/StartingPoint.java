package game;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener {

	private Image i;
	private Graphics doubleG;
	
	Jugador personaje1;
	//Jugador personaje2;
	
	Ground p[] = new Ground [7];
	Coin c[] = new Coin [3];
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		setSize (800,600);
		addKeyListener(this);
	}
	
	@Override
	public void start() {
		personaje1 = new Jugador();
		
		for (int i = 0; i<p.length; i++) {
			Random r = new Random ();
			p[i] = new Ground(getWidth()+200*i, getHeight()-40 - r.nextInt(400));
		}
		
		for (int i = 0; i<c.length; i++) {
			Random r = new Random ();
			c[i] = new GravUp(getWidth()+2000*i);
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {	
		//Thread information
		while (true) {
			
			for (int i = 0; i < c.length; i++) {
				if (c[i].getY() == this.getHeight() + 100) {
					c[i]= null;
					c[i]=new GravUp (getWidth());
				}
			}
			
			personaje1.update(this);			

			for (int i = 0; i < p.length; i++) {
				p[i].update(this, personaje1);
			}
			
			for (int i = 0; i < c.length; i++) {
				c[i].update(this, personaje1);
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
		personaje1.paint(g);
		
		for (int i = 0; i < p.length; i++) {
			p[i].paint(g);
		}
		
		for (int i = 0; i < c.length; i++) {
			c[i].paint(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_LEFT:
			personaje1.moveLeft();
			break;
			
		case KeyEvent.VK_RIGHT:
			personaje1.moveRight();
			break;
			
		case KeyEvent.VK_UP:
			personaje1.moveUp();
			break;
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
