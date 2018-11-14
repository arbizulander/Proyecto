package opcionesMenu;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import java.awt.Button;
import java.awt.Font;

public class Menu extends Applet implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font fuente;
	private Button btnIniciar, btnSalir; //Botones del Applet
	
	
	public void init() {
		add(btnIniciar = new Button("Iniciar"));
		btnIniciar.addActionListener(this);
		add(btnSalir = new Button("Salir"));
		btnSalir.addActionListener(this);
		
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("C:\\Users\\ik_2dm3\\Desktop\\Proyecto_PSP\\Proyecto\\img\\cursor.png").getImage(),
				new Point(0,0),"custom cursor"));
		
		fuente = new Font("Verdana", Font.BOLD, 26);//tipo letra
	}
	
	
	public void paint(Graphics g) {
		setBackground(Color.GRAY);//color de fondo
		g.clearRect(0, 0, 400, 400);
		g.setFont(fuente); //fuente
		this.setSize(1000,1000);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnIniciar){	
			//iniciar juego
			btnIniciar.setLabel("Continuar");	
		}
		
		if (e.getSource() == btnSalir) {			
			//salir del juego
			btnSalir.setLabel("Continuar");						
		}
	}
}
