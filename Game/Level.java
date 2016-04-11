package Game;
import java.awt.Graphics;
import java.util.ArrayList;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Level 
{
	private ArrayList<Area> areas;
	private Area currentArea;
	private Gameboard gameboard;
	private String musicFile;
	
	public Level(ArrayList<Area> areas, Gameboard gameboard, String musicFile)
	{
		this.areas = areas;
		this.gameboard = gameboard;
		this.musicFile = musicFile;
		if(areas.size() > 0)
		{
			this.currentArea = areas.get(0);
			displayArea(currentArea,gameboard.getGraphics(), gameboard);
		}
	}
	public Level(ArrayList<Area> areas, Gameboard gameboard)
	{
		this(areas, gameboard, null);
	}
	public void addArea(Area area)
	{
		this.areas.add(area);
	}
	public ArrayList<Area> getAreas()
	{
		return areas;
	}
	public Area getCurrentArea()
	{
		return currentArea;
	}
	public void setCurrentArea(int num)
	{
		currentArea = areas.get(num);
	}
	public void displayArea(Area area, Graphics g, Gameboard comp)
	{
		if(area != null)
		{
			area.draw(g, comp);
			if(musicFile != null)
				playMusic();
		}
	}
	public void playMusic()
	{
		 try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile).getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.loop(15);
		        clip.start();
		    } catch(Exception ex) {
		        ex.printStackTrace();
		    }
	}
	public String getMusic()
	{
		return musicFile;
	}
	public void setMusic(String music)
	{
		this.musicFile = music;
	}
	public Gameboard getGameboard()
	{
		return gameboard;
	}
}
