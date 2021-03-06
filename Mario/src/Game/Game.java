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
 * @author Tyler And Jacob
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
	private static int levelIndex;
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
	 * @return index number in iteration
	 */
	public int getLevelIndex(){
		return levelIndex;
	}
	/**
	 * set the current index to a number n
	 * @param n The index to set the LevelIndex to
	 */
	public void setLevelIndex(int n){
		levelIndex = n;
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
	 * <br>Must be called for every Level in the Game
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
		mario.reset();
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
		ArrayList<Area> Level_3 = new ArrayList<Area>();
		ArrayList<Area> Level_4 = new ArrayList<Area>();
		ArrayList<Area> Level_5 = new ArrayList<Area>();
		Game g = new Game();
		
		//First Level
		Area titleScreen = new Area("Images\\Levels\\TitleScreen.png");
		Area startingAreaLevel1 = new Area("Images\\Levels\\StartingAreaLevel1.png");
		Area area = new Area("Images\\Levels\\TylerLevel1-1.png");		
		Area area2 = new Area("Images\\Levels\\TylerLevel1-2.png");
		Area area3 = new Area("Images\\Levels\\TylerLevel1-3.png");
		Area gameOverScreen = new Area("Images\\Levels\\gameOverScreen.png");
		
		//Creating First Level
		titleScreen.createArea(titleScreen, g);
		startingAreaLevel1.createArea(startingAreaLevel1, g);
		Pipe p1 = new Pipe(580,480,g,area3);
		Pipe p2 = new Pipe(30,480,g,area);
		p1.setLinkedPipe(p2);
		p2.setLinkedPipe(p1);
		area.addObstacle(p1);
		area3.addObstacle(p2);
		area.createArea(area, g);
		area2.createArea(area2, g);
		area3.createArea(area3, g);
		gameOverScreen.createArea(gameOverScreen, g);
		
		
		//Adding Areas to First Level
		Level_1.add(titleScreen);
		Level_1.add(startingAreaLevel1);
		Level_1.add(area);
		Level_1.add(area2);
		Level_1.add(area3);
		Level_1.add(gameOverScreen);
		
		//Second Level
		Area startingAreaLevel2 = new Area("Images\\Levels\\StartingAreaLevel2.png");
		area = new Area("Images\\Levels\\TylerLevel2-1.png");		
		area2 = new Area("Images\\Levels\\TylerLevel2-2.png");
		area3 = new Area("Images\\Levels\\TylerLevel2-3.png");
		
		//Creating 2nd Level
		Pipe p3 = new Pipe(550,480,g,area3);
		Pipe p4 = new Pipe(40,65,g,area);
		area.addObstacle(p3);
		area3.addObstacle(p4);
		area.createArea(area, g);
		area2.createArea(area2, g);
		area3.createArea(area3, g);
		startingAreaLevel2.createArea(startingAreaLevel2, g);
		
		//Adding Areas to Second Level
		Level_2.add(startingAreaLevel2);
		Level_2.add(area);
		Level_2.add(area2);
		Level_2.add(area3);
		Level_2.add(area3);
		Level_2.add(gameOverScreen);
		
		
		//3rd Level
		Area startingAreaLevel3 = new Area("Images\\Levels\\StartingAreaLevel3.png");
		area = new Area("Images\\Levels\\JacobLevel3-1.png");		
		area2 = new Area("Images\\Levels\\JacobLevel3-2.png");
		area3 = new Area("Images\\Levels\\JacobLevel3-3.png");
		
		//Creating 3rd Level
		area.createArea(area, g);
		area2.createArea(area2, g);
		area3.createArea(area3, g);
		startingAreaLevel3.createArea(startingAreaLevel3, g);
		
		//Adding Areas to Third Level
		Level_3.add(startingAreaLevel3);
		Level_3.add(area);
		Level_3.add(area2);
		Level_3.add(area3);
		Level_3.add(area3);
		Level_3.add(gameOverScreen);
		
		//4th Level
		Area startingAreaLevel4 = new Area("Images\\Levels\\StartingAreaLevel4.png");
		area = new Area("Images\\Levels\\JacobLevel4-1.png");		
		area2 = new Area("Images\\Levels\\JacobLevel4-2.png");
		area3 = new Area("Images\\Levels\\JacobLevel4-3.png");
		
		//Creating 4th Level
		area.createArea(area, g);
		area2.createArea(area2, g);
		area3.createArea(area3, g);
		startingAreaLevel4.createArea(startingAreaLevel4, g);
		
		//Adding Areas to Fourth Level
		Level_4.add(startingAreaLevel4);
		Level_4.add(area);
		Level_4.add(area2);
		Level_4.add(area3);
		Level_4.add(area3);
		Level_4.add(gameOverScreen);
		
		//LastLevel with end
		Area startingAreaLevel5 = new Area("Images\\Levels\\StartingAreaLevel5.png");
		Area winningScreen = new Area("Images\\Levels\\WinningScreen.png");
		
		startingAreaLevel5.createArea(startingAreaLevel5, g);
		winningScreen.createArea(winningScreen, g);
		
		Level_5.add(startingAreaLevel5);
		Level_5.add(winningScreen);
		
		//Adding the Levels to the Game
		Level level = new Level(Level_1, g.getGameboard(),"Audio\\Music.wav");
		Level level2 = new Level(Level_2, g.getGameboard(),"Audio\\Music.wav");
		Level level3 = new Level(Level_3, g.getGameboard(),"Audio\\Music.wav");
		Level level4 = new Level(Level_4, g.getGameboard(),"Audio\\Music.wav");
		Level level5 = new Level(Level_5, g.getGameboard(),"Audio\\Music.wav");
		
		g.addLevel(level);
		g.addLevel(level2);
		g.addLevel(level3);
		g.addLevel(level4);
		g.addLevel(level5);
		
		//Plays every Level
		for(levelIndex = 0; levelIndex < g.getLevels().size(); levelIndex ++){
				g.play(g.getLevels().get(levelIndex));
			
		}
		g.dispose();
	}
	
}
