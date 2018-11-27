package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Score extends Coin{
	
	private StartingPoint appletInfo;

	public Score(int x, StartingPoint appletInfo) {
		super(x);
		// TODO Auto-generated constructor stub
		this.appletInfo = appletInfo;
	}

	public void performAction (Jugador j) {
		Random r = new Random();
		appletInfo.setScore(appletInfo.getScore() + 500 + r.nextInt(2000));		
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.BLUE);
		super.paint(g);
	}
	
}
