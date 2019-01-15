package game;

import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;

public class Pictures {
	
	static Image platform, ball;
	URL url;
	static StartingPoint sp;
	static AudioClip coin, music;
	
	public Pictures(StartingPoint sp) {
		
		try {
			url = sp.getDocumentBase();
		}catch (Exception e) {
			
		}
		
		coin = sp.getAudioClip(url, "music/coin.au");
		music = sp.getAudioClip(url, "music/Monkey-Drama.au");
		
		platform = sp.getImage(url, "images/brick5.png");
		ball = sp.getImage(url, "images/coin.png");
	}

}
