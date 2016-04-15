package Game;

import java.util.ArrayList;
import javax.swing.JFrame;
import Character.*;
import Obstacles.Pipe;
/**
 * The class containing the Game loop and extends JFrame
 *
 */
public class Game extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private Mario mario;
	private ArrayList<Level> levels;
	private Level currentLevel;
	private Scoreboard score;
	private Gameboard gameBoard;
	private int displayTime = 0, loop = 0;
	private double actualTime = 0.0;
	/**
	 * 
	 * @param levels An ArrayList of the Levels that will be in this Game
	 */
	public Game(ArrayList<Level> levels)
	{
		this.levels = levels;
		score = new Scoreboard();
		mario = new Mario(20,400,this);
		if(levels.size() > 0 && levels.get(0).getAreas().size() > 0)
		{
			currentLevel = levels.get(0);
			this.gameBoard = new Gameboard(mario, currentLevel);
		}
		else
		{
			this.gameBoard = new Gameboard(mario, null);
		}
		add(this.gameBoard);
		
		setSize(960, 640);
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Alternative constructor that limits adding levels to just the addLevel(Level) method
	 */
	public Game()
	{
		this(new ArrayList<Level>());
	}
	/**
	 * 
	 * @return All of the Current Levels in the Game
	 */
	public ArrayList<Level> getLevels()
	{
		return levels;
	}
	/**
	 * 
	 * @return The Scoreboard of the Game
	 */
	public Scoreboard getScoreboard()
	{
		return score;
	}
	/**
	 * 
	 * @return The Mario of the Game
	 */
	public Mario getMario()
	{
		return mario;
	}
	/**
	 * 
	 * @return The Gameboard being used
	 */
	public Gameboard getGameboard()
	{
		return gameBoard;
	}
	/**
	 * Adds a Level to the levels ArrayList
	 * @param level The Level to be added
	 */
	public void addLevel(Level level)
	{
		levels.add(level);
	}
	/**
	 * Displays the Specified Level on the Gameboard
	 * @param level The Level to be displayed on the Gameboard
	 */
	public void displayLevel(Level level)
	{
		remove(this.gameBoard);
		currentLevel = level;
		this.mario.setLocation(20, 300);
		this.gameBoard = new Gameboard(this.mario,currentLevel);
		
		
		add(this.gameBoard);
		setVisible(true);
	}
	/**
	 * 
	 * @return The Level that is currently being displayed on the Gameboard
	 */
	public Level getCurrentLevel()
	{
		return currentLevel;
	}
	/**
	 * Runs the Game loop.  Keeps track of the loop number that it is on as well as the total time elapsed since starting it
	 * <br>Must be called for every Level in the Game</br>
	 * @param level The Level to be played
	 */
	public void play(Level level)
	{
		currentLevel = level;
		currentLevel.setCurrentArea(0);
		displayLevel(currentLevel);
		loop = 0;
		displayTime = 0;
		actualTime = 0.0;
		while(mario.isAlive()) //Add later: while(mario.isAlive() && !levelOver)
		{
			loop++;
			long start = System.currentTimeMillis();
			mario.move(this.gameBoard.getActiveKeys());
			mario.tick();	
			for(Enemy e : currentLevel.getCurrentArea().getEnemies())
				e.tick();
			gameBoard.repaint();
			try 
			{
				Thread.sleep(20);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			actualTime += (end-start)/1000.0;
			displayTime = (int)actualTime;
		}
	}
	/**
	 * 
	 * @return The time elapsed in the game loop as an int
	 */
	public int getDisplayTime()
	{
		return displayTime;
	}
	/**
	 * 
	 * @return The time elapsed in the game loop as a double
	 */
	public double getActualTime()
	{
		return actualTime;
	}
	/**
	 * 
	 * @return The number of the loop that the game loop is currently on
	 */
	public int getLoopNumber()
	{
		return loop;
	}
	public static void main(String[] args)
	{
		ArrayList<Area> areas = new ArrayList<Area>();
		Game g = new Game();
		
		Area area = new Area("TestLevel.png");
		
		Area area2 = new Area("TestLevel2.png");
		Pipe p1 = new Pipe(250, 420, g, area2);
		Pipe p2 = new Pipe(250,420,g);
		p1.setLinkedPipe(p2);
		area.addObstacle(p1);
		area2.addObstacle(p2);
		area.createLevel(area, g);
		area2.createLevel(area2, g);
		areas.add(area);
		areas.add(area2);
		
		
		
		Level level = new Level(areas, g.getGameboard(),"Music.wav");
		g.addLevel(level);
		for(Level lev : g.getLevels())
			g.play(lev);
	}
	
}
