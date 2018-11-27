package game;

import java.awt.Color;
import java.awt.Graphics;

public class AgilUp extends Coin{

	public AgilUp(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public void performAction(Jugador j) {
		if (j.getAgility() < 8) {
			j.setAgility(j.getAgility() + 1);
		}		
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.ORANGE);
		super.paint(g);
	}	
}
