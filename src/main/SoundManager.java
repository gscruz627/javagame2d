package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {

	Clip clip;
	URL[] soundURL = new URL[30];
	
	public SoundManager() {
		soundURL[0] = getClass().getResource("/sound/theme.wav");
		soundURL[1] = getClass().getResource("/sound/card.wav");
		soundURL[2] = getClass().getResource("/sound/door.wav");
		soundURL[3] = getClass().getResource("/sound/pop.wav");
		soundURL[4] = getClass().getResource("/sound/card.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(input);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}

}
