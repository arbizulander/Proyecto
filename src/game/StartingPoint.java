package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;


public class StartingPoint extends Applet implements Runnable, KeyListener {

	private Image i;
	private Graphics doubleG;
	
	Jugador personaje1;
	//Jugador personaje2;
	
	Ground p[] = new Ground [7];
	Coin c[] = new Coin [3];
	private int score;
	double cityX = 0;
	double cityDx = 3;
	
	URL url;
	Image city;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		setSize (800,600);
		addKeyListener(this);
		try {
			url = getDocumentBase();
			System.out.println("RUTA: "+url);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		city = getImage(url, "images/back3.png");
	}
	
	@Override
	public void start() {
		personaje1 = new Jugador();
		score = 0;
		
		for (int i = 0; i<p.length; i++) {
			Random r = new Random ();
			p[i] = new Ground(getWidth()+200*i, getHeight()-40 - r.nextInt(400));
		}
		
		for (int i = 0; i<c.length; i++) {
			Random r = new Random ();
			
			switch (r.nextInt(5)) {
			case 0:
				c[i]=new GravUp (getWidth()+2000*i);
				break;
			case 1:
				c[i]=new GravDown (getWidth()+2000*i);
				break;
			case 2:
				c[i]=new AgilUp (getWidth()+2000*i);
				break;
			case 3:
				c[i]=new AgilDown (getWidth()+2000*i);
				break;	
			case 4:
				c[i]=new Score (getWidth()+2000*i, this);
				break;				
			}	
			c[i] = new GravUp(getWidth()+2000*i);
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {	
		//Thread information
		while (true) {
			
			if (cityX > getWidth()*-1) {
				cityX -= cityDx;
			}
			else {
				cityX = 0;
			}
			
			score++;
			Random r = new Random();
			
			
			for (int i = 0; i < c.length; i++) {
				if (c[i].getY() == this.getHeight() + 100) {
					c[i]= null;
					switch (r.nextInt(5)) {
					case 0:
						c[i]=new GravUp (getWidth() + 10* r.nextInt(500));
						break;
					case 1:
						c[i]=new GravDown (getWidth()+ 10* r.nextInt(500));
						break;
					case 2:
						c[i]=new AgilUp (getWidth()+ 10* r.nextInt(500));
						break;
					case 3:
						c[i]=new AgilDown (getWidth()+ 10* r.nextInt(500));
						break;	
					case 4:
						c[i]=new Score (getWidth()+ 10* r.nextInt(500), this);
						break;
						
					}
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
				
		g.setColor(new Color(15, 77, 147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city,(int) cityX,0, this);
		g.drawImage(city,(int) cityX+getWidth(),0, this);
		
		for (int i = 0; i < p.length; i++) {
			p[i].paint(g);
		}
		
		for (int i = 0; i < c.length; i++) {
			c[i].paint(g);
		}
		
		personaje1.paint(g);
		String sc = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(sc, getWidth() -150+1, 50+1);
		g.setColor(Color.GRAY);
		g.drawString(sc, getWidth() -150, 50);
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

	//Getters
	public int getScore() {
		return score;
	}

	
	//Setters
	public void setScore(int score) {
		this.score = score;
	}

	
	
	
}
