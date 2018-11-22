package game;

import java.awt.Color;
import java.awt.Graphics;

public class GravUp extends Coin{

	public GravUp(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public void paint (Graphics g) {
		g.setColor(Color.RED);
		super.paint(g);
		
	}
	
}
