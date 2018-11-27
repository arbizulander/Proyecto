package game;

import java.awt.Color;
import java.awt.Graphics;

public class AgilDown extends Coin{

	public AgilDown(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public void performAction(Jugador j) {
		if (j.getAgility() >= 2) {
			j.setAgility(j.getAgility() - 1);
		}		
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.RED);
		super.paint(g);
		
	}	
}
