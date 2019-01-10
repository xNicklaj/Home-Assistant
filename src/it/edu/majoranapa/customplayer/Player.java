package it.edu.majoranapa.customplayer;

import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import it.edu.majoranapa.io.Volume;

public class Player{
	private MediaPlayer player;
	private JFXPanel panel;
	
	public Player(Media audiofile)
	{
		this.player = new MediaPlayer(audiofile);
	}

	public int setVolumePercentage(float volume)
	{
		if(volume < 0.0 || volume > 100.0)
			return -1;
		
		Volume.setMediaVolumePercentage(volume);
		player.setVolume(volume/100);
		return 0;
	}
	
	public void playMedia()
	{
		player.play();
	}
	
	public void pauseMedia()
	{
		player.pause();
	}
	
	public void toggleMute()
	{
		if(!player.isMute())
			player.setMute(true);
		else
			player.setMute(false);
	}
}
