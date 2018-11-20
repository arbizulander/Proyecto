package opcionesMenu;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import pantallaJuego.InterfazJuego;

import java.awt.Button;
import java.awt.Font;

public class Menu extends Applet implements Runnable{
	
	int x = 0;
	int y = 0;
	int dx = 2;
	int dy = 2;
	int radious = 10;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		
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
			repaint();
			try {
				Thread.sleep(17);
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
	public void paint(Graphics g) {
	
	}

	
	
	
	/*public static final String MAP_PATH = "res/maps/";
	public static final int SIZE_X = 400;
	public static final int SIZE_Y = 400;
	
	private Font fuente;
	private Button btnIniciar, btnSalir,btnPrueba; //Botones del Applet
	private Label carac;
	
	private InterfazJuego Interfaz;
	
	private char[][] coordenadas;
	
	public void init() {
		//add(btnIniciar = new Button("Iniciar"));
		//btnIniciar.addActionListener(this);
		//add(btnSalir = new Button("Salir"));
		//btnSalir.addActionListener(this);
		
		String packageDirectory = getClass()
				.getClassLoader().getResource("")
				.getPath();		
		packageDirectory = packageDirectory.substring(0,
				packageDirectory.lastIndexOf("bin/"));
				
		String ruta_cursor = packageDirectory+"res/img/cursor.png";		
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(ruta_cursor).getImage(),
				new Point(0,0),"custom cursor"));
		
		fuente = new Font("Verdana", Font.BOLD, 26);//tipo letra
		
		char [][] coordenadas = new char[SIZE_X][SIZE_Y];
		for (int i=0; i<SIZE_Y; i++) {
			for (int j=0; j<SIZE_X; j++) {
				
			}	
		}
		String packageDirectoryMap = getClass()
				.getClassLoader().getResource("")
				.getPath();
		
		packageDirectoryMap = packageDirectoryMap.substring(0,
				packageDirectoryMap.lastIndexOf("bin/"));
		
		System.out.println(packageDirectoryMap+MAP_PATH+"lvl_1.txt");
		String rutaMapa = packageDirectoryMap+MAP_PATH+"lvl_1.txt";
		try {
			muestraContenido(rutaMapa);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


	/*public void paint(Graphics g) {
		setBackground(Color.GRAY);//color de fondo
		g.clearRect(0, 0, SIZE_Y, SIZE_X);
		g.setFont(fuente); //fuente
		this.setSize(SIZE_Y, SIZE_X);		
	}*/
	
	
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnIniciar){	
			//iniciar juego
			//Interfaz.start();
			try {
				String packageDirectory = getClass()
						.getClassLoader().getResource("")
						.getPath();
				
				packageDirectory = packageDirectory.substring(0,
						packageDirectory.lastIndexOf("bin/"));
				
				System.out.println(packageDirectory+MAP_PATH+"lvl_1.txt");
				String rutaMapa = packageDirectory+MAP_PATH+"lvl_1.txt";
				muestraContenido (rutaMapa);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//btnIniciar.setLabel("Continuar");	
		}
		
		if (e.getSource() == btnSalir) {			
			//salir del juego
			//btnSalir.setLabel("Continuar");						
		}
	}*/
	
	
	
	/*public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;       
        FileReader f = new FileReader(archivo);

        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
        	leerLinea (cadena);
        }
        b.close();
    }
	
	public static void leerLinea (String linea) {
			char[] carac = linea.toCharArray();
		for (int i = 0; i<linea.length(); i++) {
			switch (carac[i]) {
			case '*':
				//add();
				break;
			case 'G':
				break;
			}
		}
	}*/
	
	/*public void llenarMapa(String linea, int num, String lvl){
		
			try {
				muestraContenido(lvl);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			for (int j=0; j<linea.length(); j++) {
				coordenadas [num][j] = linea.charAt(j); 
			}		
		
		
	}*/
}
