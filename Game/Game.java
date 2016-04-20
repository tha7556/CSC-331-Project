package Game;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Characters.*;
import Obstacles.*;
import Items.Item;
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
		ImageIcon icon = new ImageIcon("Images\\MarioSymbol.png");
		this.setIconImage(icon.getImage());
		this.levels = levels;
		score = new Scoreboard(this);
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
		
		setLayout(new BorderLayout());
		add(score,"North");
		
		setSize(960, 690);
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
		setVisible(false);
		remove(this.gameBoard);
		currentLevel = level;
		this.mario.setLocation(20, 450);
		this.gameBoard = new Gameboard(this.mario,currentLevel);
		if(level.getMusic() != null)
			level.playMusic();
		
		
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
		score.reset();
		while(mario.isAlive() && !currentLevel.isFinished())
		{
			loop++;
			long start = System.currentTimeMillis();
			mario.move(this.gameBoard.getActiveKeys());
			mario.tick();	
			for(Enemy e : currentLevel.getCurrentArea().getEnemies())
				if(e.isVisible())
					e.tick();
			for(Obstacle o : currentLevel.getCurrentArea().getObstacles())
			{
				if(o instanceof FinishLine && o.isVisible())
				{
					FinishLine f = (FinishLine)o;
					f.tick();
				}
				else if(o instanceof QuestionBlock && o.isVisible())
				{
					QuestionBlock q = (QuestionBlock)o;
					Item item = q.getItem();
					if(item.isAlive() && item.isVisible())
						item.tick();
				}
			}
			gameBoard.repaint();
			score.repaint();
			try 
			{
				Thread.sleep(15);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			if(score.isVisibility() == true){
				actualTime += (end-start)/1000.0;
				displayTime = (int)actualTime;
			}
		}
		//End of Level:
		mario.reset(30, 450);
		currentLevel.stopMusic();
		setVisible(false);
	}
	public void resetActualTime(){
		actualTime = 0;
		displayTime = 0;
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
		ArrayList<Area> Level_1 = new ArrayList<Area>();
		ArrayList<Area> Level_2 = new ArrayList<Area>();
		Game g = new Game();
		
		//First Level
		Area titleScreen = new Area("Images\\TitleScreen.png");
		Area gameOverScreen = new Area("Images\\gameOverScreen.png");
		Area WinningScreen = new Area("Images\\WinningScreen.png");
		Area tutorialLevel = new Area("Images\\TutorialLevel.png");
		Area area = new Area("Images\\TestLevel.png");		
		Area area2 = new Area("Images\\TestLevel2.png");
		Area area3 = new Area("Images\\TestLevel3.png");	
		
		//Creating First Level
		titleScreen.createArea(titleScreen, g);
		gameOverScreen.createArea(gameOverScreen, g);
		WinningScreen.createArea(WinningScreen, g);
		tutorialLevel.createArea(tutorialLevel, g);
		area.createArea(area, g);
		area2.createArea(area2, g);
		area3.createArea(area3, g);
		
		
		
		//Adding Areas to First Level
		Level_1.add(titleScreen);
		Level_1.add(gameOverScreen);
		Level_1.add(WinningScreen);
		Level_1.add(tutorialLevel);
		Level_1.add(area);
		Level_1.add(area2);
		Level_1.add(area3);
		
		
		
		area = new Area("Images\\Area2-1.png");		
		area2 = new Area("Images\\Area2-2.png");
		area3 = new Area("Images\\Area2-3.png");
		area.createArea(area, g);
		area2.createArea(area2, g);
		area3.createArea(area3, g);
		Level_2.add(titleScreen);
		Level_2.add(gameOverScreen);
		Level_2.add(WinningScreen);
		Level_2.add(tutorialLevel);
		Level_2.add(area);
		Level_2.add(area2);
		Level_2.add(area3);
		
		
		
		Level level = new Level(Level_1, g.getGameboard(),"Audio\\Music.wav");
		Level level2 = new Level(Level_2, g.getGameboard(),"Audio\\Music.wav");
		
		g.addLevel(level);
		g.addLevel(level2);
		
		
		for(int index = 0; index < g.getLevels().size(); index ++){
			if(index == 0){
				g.play(g.getLevels().get(index));
			}
			
			else{
				g.play(g.getLevels().get(index));
				g.getCurrentLevel().setCurrentIndex(3);
				g.getCurrentLevel().setCurrentArea(3);
			}
		}
		g.dispose();
	}
	
}
