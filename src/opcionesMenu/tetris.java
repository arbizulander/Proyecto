package opcionesMenu;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
* @author alex masanov for dhtml.itsolutions.ru
*/

public class tetris extends Applet implements KeyListener {
	
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public final byte WIDTH = 20; // pixels
	public final byte HEIGHT = 20;
	public final byte V_NUMBER = 20;
	public final byte H_NUMBER = 12;
	public final byte FIGDIM = 3;
	
	public final byte MENU = 1;
	public final byte PLAYING = 11;
	public final byte END = 111;
	
	public final byte STILL = 0; 
	public final byte LEFT = 1;
	public final byte RIGHT = 2;
	public final byte DOWN = 3;
	
	public Color[] colors = {
	new Color(0xff, 0x00, 0x00),
	new Color(0x00, 0xff, 0x00), 
	new Color(0x00, 0x00, 0xff),
	new Color(0xff, 0xff, 0x00),
	new Color(0xff, 0x00, 0xff),
	new Color(0x00, 0xff, 0xff),
	new Color(0xff, 0x99, 0x33)};
	
	public Color colorbg = new Color(0x0, 0x0, 0x0);
	public Color colorhelp = new Color(0x44, 0x44, 0x44);
	
	private Image imgBuf;
	private Graphics hdcbuf;
	private Graphics hdc;
	private Rectangle sz;
	
	private boolean bShowHelp = true;
	
	////////////////////////////
	//	Game attributes
	////////////////////////////
	
	boolean gamefield[][] = new boolean[H_NUMBER][V_NUMBER];
	Color gamefieldcolors[][] = new Color[H_NUMBER][V_NUMBER];
	public byte gamestate;
	private int score, topscore;
	
	Figure figure;
	FigureThread fthread = null;
	
	public void init()
	{
		sz = new Rectangle(0, 0, 0, 0);
		sz.width = WIDTH*H_NUMBER;
		sz.height = HEIGHT*V_NUMBER;
		resize(sz.width, sz.height);
		
		imgBuf = createImage(sz.width, sz.height);
		hdcbuf = imgBuf.getGraphics();
		hdc = getGraphics();
		
		addKeyListener(this);
		
		
		gamestate = MENU;
		drawMenu();
	}
	
	public void keyPressed(KeyEvent e) 
	{
		if(gamestate != PLAYING)
		newgame();
		if(gamestate == PLAYING)
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
			figure.rotate();
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				figure.vector = LEFT;
				if(canMove(figure)){
					figure.x--;
					drawScreen();
				}	
			}
			if(e.getKeyCode() == KeyEvent.VK_F1)
			{
				bShowHelp = !bShowHelp;
				drawScreen();	
			}	
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{ 
				figure.vector = RIGHT;
				if(canMove(figure)){
					figure.x++;
					drawScreen();
				}	
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				figure.y = stopRow(figure);
				drawScreen();
			}
		}
		
	}
	public void keyReleased(KeyEvent e){;}
	public void keyTyped(KeyEvent e){;}
	
	public void paint(Graphics g)
	{
		g.drawImage(imgBuf, 0, 0, null);
	}
	
	
	public void drawMenu()
	{
		Color green = new Color(0x00, 0xff, 0x00);
		hdcbuf.setColor(colorbg);
		hdcbuf.fillRect(0, 0, H_NUMBER*WIDTH, V_NUMBER*HEIGHT);
		hdcbuf.setColor(green);
		hdcbuf.drawRect(0, 0, H_NUMBER*WIDTH-1, V_NUMBER*HEIGHT-1);
		
		hdcbuf.drawString("Java Tetris v. 1.0", 20, 20);
		hdcbuf.drawString("Controls: ", 50, 50);
		hdcbuf.drawString("Left arrow: move left", 50, 80);
		hdcbuf.drawString("Right arrow: move right", 50, 100);
		hdcbuf.drawString("Up arrow: rotate", 50, 120);
		hdcbuf.drawString("Down arrow / space: quick drop", 50, 140);
		hdcbuf.drawString("F1: toggle help figure", 50, 160);
		
		
		hdcbuf.drawString("Please click the applet and", 50, 280);
		hdcbuf.drawString("press any key to start!", 60, 300);
		
	
	}
	public void drawScreen()
	{
		if(gamestate == PLAYING)
		{
			int i, j;
			Rectangle rect = new Rectangle(0, 0, 0, 0);
			// gamefield
			for(i=0; i<H_NUMBER; i++)
				for(j=0; j<V_NUMBER; j++){
					rect.y = j*HEIGHT;
					rect.x = i*WIDTH;
					rect.width = WIDTH;
					rect.height = HEIGHT;
					
					hdcbuf.setColor(gamefieldcolors[i][j]);
					hdcbuf.fillRect(rect.x, rect.y, rect.width, rect.height);
					
					hdcbuf.setColor(colorbg);
					hdcbuf.drawRect(rect.x, rect.y, rect.width, rect.height);
				
			}
			
			if(bShowHelp){
				// help figure
				for(i=0; i<figure.Dim; i++)
					for(j=0; j<figure.Dim; j++)
					if(figure.area[i][j]){
						rect.y = (j+stopRow(figure))*HEIGHT;
						rect.x = (i+figure.x)*WIDTH;
						rect.width = WIDTH;
						rect.height = HEIGHT;
						
						hdcbuf.setColor(colorhelp);
						hdcbuf.fillRect(rect.x, rect.y, rect.width, rect.height);
						
						hdcbuf.setColor(colorbg);
						hdcbuf.drawRect(rect.x, rect.y, rect.width, rect.height);
						
						}	
			
			}
			
			
			// figure itself
			for(i=0; i<figure.Dim; i++)
				for(j=0; j<figure.Dim; j++)
					if(figure.area[i][j]){
					rect.y = (j+figure.y)*HEIGHT;
					rect.x = (i+figure.x)*WIDTH;
					rect.width = WIDTH;
					rect.height = HEIGHT;
					
					hdcbuf.setColor(colors[figure.fignumber-1]);
					hdcbuf.fillRect(rect.x, rect.y, rect.width, rect.height);
					
					hdcbuf.setColor(colorbg);
					hdcbuf.drawRect(rect.x, rect.y, rect.width, rect.height);
					
					}	
			
			// text
			hdcbuf.setColor(new Color(0x0, 0xff, 0x0));
			hdcbuf.drawString("Score: " + score, 10, 25);
			//hdcbuf.drawString("Top: " + topscore, 24, 10);
			
			// frame
			hdcbuf.setColor(colorhelp);
			hdcbuf.drawRect(0, 0, H_NUMBER*WIDTH-1, V_NUMBER*HEIGHT-1);
			
			paint(hdc);
		}
	}
	
	void newgame()
	{
		int i, j;
		score = 0;
		for(i=0; i<H_NUMBER; i++)
		for(j=0; j<V_NUMBER; j++){
			gamefield[i][j] = false;
			gamefieldcolors[i][j] = colorbg;
		}
	
		gamestate = PLAYING;
		figure = new Figure();
		drawScreen();
		
		fthread = new FigureThread();
		fthread.start(); 
	}
	
	void addBricks(Figure f)
	{
		int i, j;
		for(i=0; i<f.Dim; i++)
		for(j=0; j<f.Dim; j++)
			
		if(f.area[i][j]){
			gamefield[f.x+i][f.y+j]=true;
			gamefieldcolors[f.x+i][f.y+j]=colors[f.fignumber-1];
		}
	}
	
	boolean figOK(Figure f)
	{
		int i,j;
		
		for(i=0; i<f.Dim; i++)
		for(j=0; j<f.Dim; j++)
		if(f.area[i][j] && gamefield[f.x+i][f.y+j]) 
		return false;
		
		return true;
	
	}
	
	void removeLines()
	{
		int i,j,j2, count;
		
		for(j=0; j<V_NUMBER; j++)
		{
			for(i=0, count=0; i<H_NUMBER; i++)
			if(gamefield[i][j]) 
			count++;
			
			if(count == H_NUMBER)
			{
				score+=10;
				if(score > topscore)
				topscore = score;
				
				for(i=0; i<H_NUMBER; i++)
				gamefield[i][j] = false;
				for(j2=j; j2>0; j2--)
				for(i=0; i<H_NUMBER; i++){
				gamefield[i][j2] = gamefield[i][j2-1];
				gamefieldcolors[i][j2] = gamefieldcolors[i][j2-1];
				}
			}
		}
	}
	
	boolean canMove(Figure f)
	{
		int i,j;
		
		for(i=0; i<f.Dim; i++)
		for(j=0; j<f.Dim; j++)
		if(f.area[i][j])
		switch(f.vector){
		case RIGHT:
		if(f.x+i+1 == H_NUMBER || gamefield[f.x+i+1][f.y+j])
		return false;
		break;
		case DOWN:
		if(f.y+j+1 == V_NUMBER || gamefield[f.x+i][f.y+j+1])
		return false;
		break;
		case LEFT:
		if((f.x+i)==0 || gamefield[f.x+i-1][f.y+j])
		return false;
		break;
		}
		
		return true;
		
	}
	
	int stopRow(Figure f)
	{
	Figure temp = new Figure();
	int i,j;
	
	temp.Dim = f.Dim;
	
	for(i=0; i<f.Dim; i++)
	for(j=0; j<f.Dim; j++)
	temp.area[i][j] = f.area[i][j];
	
	temp.x = f.x;
	temp.vector = DOWN;
	
	for(temp.y=f.y; canMove(temp); temp.y++)
	;
	
	return temp.y;
	}
	
	class Figure
	{
	int x,y;
	int Dim;
	int vector;
	int fignumber;
	
	int i,j;
	
	boolean[][] area = new boolean[4][4];
	
	
	Figure()
	{
	makeNew(); 
	}
	
	void makeNew()
	{
	fignumber = (int)Math.floor(Math.random()*7.0)+1; 
	
	discard();
	
	y = 0;
	x = (H_NUMBER - Dim) / 2;
	vector = STILL;
	
	}
	
	void rotate()
	{
	if(x>=0 && x<=H_NUMBER-Dim)
	{
	boolean temparea[][] = new boolean[4][4];
	for(i=0; i<Dim; i++)
	for(j=0; j<Dim; j++)
	temparea[i][j]=area[i][j];
	
	for(i=0; i<Dim; i++)
	for(j=0; j<Dim; j++)
	area[i][j]=temparea[j][Dim-1-i];
	
	if(figOK(this)) 
	drawScreen();
	else {
	for(i=0; i<Dim; i++)
	for(j=0; j<Dim; j++)
	area[i][j]=temparea[i][j];
	}	
	
	}
	}
	
	void discard()
	{
	for(i=0; i<4; i++)
	for(j=0; j<4; j++)
	area[i][j] = false;
	
	switch(fignumber)
	{
	case 1: // __|
	Dim = 3;
	area[0][2] = true;
	area[1][2] = true;
	area[2][2] = true;
	area[2][1] = true;
	break;
	case 2: // |__
	Dim = 3;
	area[0][2] = true;
	area[1][2] = true;
	area[2][2] = true;
	area[0][1] = true;
	break;
	case 3: // _|_
	Dim = 3;
	area[0][2] = true;
	area[1][2] = true;
	area[2][2] = true;
	area[1][1] = true;
	break;
	case 4: // o0
	Dim = 3;
	area[0][1] = true;
	area[0][2] = true;
	area[1][0] = true;
	area[1][1] = true;
	break;
	case 5: // 0o
	Dim = 3;
	area[1][0] = true;
	area[1][1] = true;
	area[2][1] = true;
	area[2][2] = true;
	break;
	case 6: // 88
	Dim = 2;
	area[0][0] = true;
	area[0][1] = true;
	area[1][0] = true;
	area[1][1] = true;
	break;
	case 7: // ____
	Dim = 4;
	area[1][0] = true;
	area[1][1] = true;
	area[1][2] = true;
	area[1][3] = true;
	break;
	}
	
	}
	
	}
	
	class FigureThread extends Thread
	{
	private int delay;
	
	public FigureThread()
	{
	delay = 400;
	}
	public void setDelay(int d){delay = d;}
	
	public void run()
	{
	while(gamestate == PLAYING){ 
	
	try{sleep(delay);} catch(InterruptedException e){;}
	
	figure.vector = DOWN;
	if(canMove(figure)){
	figure.y++;
	drawScreen();
	} else {
	if(figure.y == 0) 
	gamestate = END; 
	
	addBricks(figure);
	
	int tempholder = score / 100;
	
	removeLines(); 
	score++; 
	
	delay = (tempholder != score / 100) ? delay - 15 : delay;
	
	if(score > topscore)
	topscore = score;
	
	figure.makeNew();
	drawScreen();
	}
	
	
	}
	}
	}

}
