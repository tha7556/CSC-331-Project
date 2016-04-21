package Game;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * A single Level that contains different Areas
 * @author Tyler And Jacob
 *
 */
public class Level 
{
	private ArrayList<Area> areas;
	private Area currentArea;
	private int currentIndex;
	private Gameboard gameboard;
	private String musicFile;
	private Clip clip;
	private boolean finished;
	/**
	 * 
	 * @param areas ArrayList containing the Areas in this Level
	 * @param gameboard The instance of Gameboard
	 * @param music String representation of the background Audio File
	 */
	public Level(ArrayList<Area> areas, Gameboard gameboard, String music)
	{
		this.areas = areas;
		this.finished = false;
		this.gameboard = gameboard;
		this.musicFile = music;
		if(areas.size() > 0)
		{
			this.currentArea = areas.get(0);
			currentIndex = 0;
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
	
	public void setCurrentIndex(int index){
		currentIndex = index;
	}
	/**
	 * 
	 * @return The Level that is currently being displayed
	 */
	public Area getCurrentArea()
	{
		return currentArea;
	}
	public int getCurrentIndex()
	{
		return currentIndex;
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
			currentArea = area;
		}
	}
	/**
	 * Loads the Area that comes after the current one in the areas ArrayList
	 */
	public void loadNextLevel()
	{		
			if(currentIndex > -1  && currentIndex + 1 < areas.size())
			{
				displayArea(areas.get(currentIndex+1),gameboard.getGraphics(),gameboard);	
				gameboard.getMario().reset(0, gameboard.getMario().getY());
				gameboard.getMario().setRespawnPoint(areas.get(currentIndex+1).getRespawnPointX(), areas.get(currentIndex+1).getRespawnPointY());
				currentIndex++;
				return;
			}
		
		throw new RuntimeException("Next Level == null at index: " + currentIndex);
	}
	/**
	 * Loads the Area that comes before the current one in the areas ArrayList
	 */
	public void loadPreviousLevel()
	{
		if(currentIndex > 0)
		{
			displayArea(areas.get(currentIndex-1),gameboard.getGraphics(),gameboard);	
			gameboard.getMario().reset(960-gameboard.getMario().getWidth()-10, gameboard.getMario().getY());
			gameboard.getMario().setRespawnPoint(areas.get(currentIndex-1).getRespawnPointX(), areas.get(currentIndex-1).getRespawnPointY());
			currentIndex--;
			return;
		}
	
		throw new RuntimeException("Previous Level == null");
	}
	/**
	 * Loads a specific Area from the areas ArrayList
	 * @param area The Area to load
	 * @param x The x coordinate to put Mario
	 * @param y The y coordinate to put Mario
	 */
	public void loadSpecificLevel(Area area, int x, int y)
	{
		if(areas.contains(area))
		{
			displayArea(area,gameboard.getGraphics(),gameboard);
			gameboard.getMario().reset(area.getRespawnPointX(),area.getRespawnPointY());
			gameboard.getMario().setRespawnPoint(area.getRespawnPointX(), area.getRespawnPointY());
			currentIndex = areas.indexOf(area);

			return;
		}
		throw new RuntimeException("New Area == null");
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
			     clip = AudioSystem.getClip();
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
	 * Stops the Music from playing
	 */
	public void stopMusic()
	{
		clip.stop();
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
	/**
	 * 
	 * @return True if the Level is Finished, False otherwise
	 */
	public boolean isFinished()
	{
		return finished;
	}
	/**
	 * 
	 * @param f True if the Level should be finished, False otherwise
	 */
	public void setFinished(boolean f)
	{
		finished = f;
	}
}
