package Game;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * A single Level that contains different Areas
 *
 */
public class Level 
{
	private ArrayList<Area> areas;
	private Area currentArea;
	private Gameboard gameboard;
	private String musicFile;
	/**
	 * 
	 * @param areas ArrayList containing the Areas in this Level
	 * @param gameboard The instance of Gameboard
	 * @param music String representation of the background Audio File
	 */
	public Level(ArrayList<Area> areas, Gameboard gameboard, String music)
	{
		this.areas = areas;
		this.gameboard = gameboard;
		this.musicFile = music;
		if(areas.size() > 0)
		{
			this.currentArea = areas.get(0);
			displayArea(currentArea,gameboard.getGraphics(), gameboard);
		}
	}
	/**
	 * Alternative constructor without music
	 * @param areas ArrayList containing the Areas in this Level
	 * @param gameboard The instance of Gameboard
	 */
	public Level(ArrayList<Area> areas, Gameboard gameboard)
	{
		this(areas, gameboard, null);
	}
	/**
	 * Adds a single Area to the Level
	 * @param area Area to be added
	 */
	public void addArea(Area area)
	{
		this.areas.add(area);
	}
	/**
	 * 
	 * @return An ArrayList of all of the Areas in this Level
	 */
	public ArrayList<Area> getAreas()
	{
		return areas;
	}
	/**
	 * 
	 * @return The Level that is currently being displayed
	 */
	public Area getCurrentArea()
	{
		return currentArea;
	}
	/**
	 * 
	 * @param index The index of the new Area in the areas ArrayList
	 */
	public void setCurrentArea(int index)
	{
		currentArea = areas.get(index);
	}
	/**
	 * Displays the specified Area on the Gameboard
	 * @param area The Area to be displayed
	 * @param g The Graphics Object
	 * @param comp The instance of the Gameboard
	 */
	public void displayArea(Area area, Graphics g, Gameboard comp)
	{
		if(area != null)
		{
			area.draw(g, comp);
			if(musicFile != null)
				playMusic();
		}
	}
	/**
	 * Plays the background Music on a loop (15 times)
	 */
	public void playMusic()
	{
		try 
		{
			 File f = new File(musicFile);
			 if(!f.exists())
			 {
				 throw new RuntimeException("Music File not found: " + musicFile);
			 }
			 else
			 {
				 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile).getAbsoluteFile());
			     Clip clip = AudioSystem.getClip();
			     clip.open(audioInputStream);
			     clip.loop(15);
			     clip.start();
			 }
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * @return The String representation of the background Music File
	 */
	public String getMusic()
	{
		return musicFile;
	}
	/**
	 * 
	 * @param music The String representation of the background Music that is being set
	 */
	public void setMusic(String music)
	{
		this.musicFile = music;
	}
	/**
	 * 
	 * @return The instance of Gameboard used
	 */
	public Gameboard getGameboard()
	{
		return gameboard;
	}
}
