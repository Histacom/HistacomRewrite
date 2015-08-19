package ml.melonz.histacomrm;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	private AudioClip clip;
	public static final Sound win95Start = new Sound("/ml/melonz/histacomrm/Windows95Startup.wav");
	
	public Sound(String filename) {
		try{
		  clip = Applet.newAudioClip(Sound.class.getResource(filename));
		  } catch(Exception e){
		  e.printStackTrace();
		{
			
		}
	
	}
		
   }
	
   public void play() {
	   try{
		   new Thread(){
			   public void run(){
				   clip.play();
			   }
		   }.start();
	    } catch(Exception ex){
			ex.printStackTrace();
		{
				
		}
   }

}
}