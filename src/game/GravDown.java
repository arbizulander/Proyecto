package game;

import java.awt.Color;
import java.awt.Graphics;

public class GravDown extends Coin{

	public GravDown(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public void performAction(Jugador j) {
		if (j.getGravity()> 3) {
			j.setGravity(j.getGravity()-3);
			
			if (j.getGravity() < 3){
				j.setGravity(3);
			}
		}
		
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.ORANGE);
		super.paint(g);
		
	}
	
}
