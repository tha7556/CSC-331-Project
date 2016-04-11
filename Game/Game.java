package Game;

import java.util.ArrayList;
import javax.swing.JFrame;
import Character.*;
import Obstacles.*;

public class Game extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private Mario mario;
	private ArrayList<Level> levels;
	private Level currentLevel;
	private Scoreboard score;
	private Gameboard gameBoard;
	private int width = 1000, height = 600;
	private int displayTime = 0, loop = 0;
	private double actualTime = 0.0;
	public Game(ArrayList<Level> levels)
	{
		width = 1000;
		height = 600;
		this.levels = levels;
		score = new Scoreboard();
		mario = new Mario(20,400,this);
		if(levels.size() > 0 && levels.get(0).getAreas().size() > 0)
		{
			currentLevel = levels.get(0);
			this.gameBoard = new Gameboard(mario, currentLevel.getAreas().get(0));
		}
		else
		{
			this.gameBoard = new Gameboard(mario, null);
		}
		add(this.gameBoard);
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Game()
	{
		this(new ArrayList<Level>());
	}
	public ArrayList<Level> getLevels()
	{
		return levels;
	}
	public Scoreboard getScoreboard()
	{
		return score;
	}
	public Mario getMario()
	{
		return mario;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public Gameboard getGameboard()
	{
		return gameBoard;
	}
	public void addLevel(Level level)
	{
		levels.add(level);
	}
	public void displayLevel(Level level)
	{
		remove(this.gameBoard);
		currentLevel = level;
		this.mario.setLocation(20, 300);
		this.gameBoard = new Gameboard(this.mario,currentLevel.getCurrentArea());
		
		
		add(this.gameBoard);
		setVisible(true);
	}
	public Level getCurrentLevel()
	{
		return currentLevel;
	}
	
	public void play()
	{
		currentLevel = levels.get(0);
		currentLevel.setCurrentArea(0);
		displayLevel(currentLevel);
		loop = 0;
		displayTime = 0;
		actualTime = 0.0;
		while(mario.isAlive())
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
				Thread.sleep(25);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			actualTime += (end-start)/1000.0;
			displayTime = (int)actualTime;
			//System.out.println("#" + loop + ":  " + displayTime);
		}
	}
	public int getDisplayTime()
	{
		return displayTime;
	}
	public double getActualTime()
	{
		return actualTime;
	}
	public int getLoopNumber()
	{
		return loop;
	}
	public static void main(String[] args)
	{
		ArrayList<Area> areas = new ArrayList<Area>();
		Game g = new Game();

		Area area = new Area("LevelA.png");
		for(int i = 0; i < 960; i += 60)
		{
			area.addObstacle(new Ground(i,487,g));
		}
		//area.addObstacle(new QuestionBlock(75,249));
		area.addObstacle(new QuestionBlock(378,249,g));
		area.addObstacle(new QuestionBlock(497,249,g));
		area.addObstacle(new QuestionBlock(440,9,g));
		area.addObstacle(new Brick(432,249,g));
		area.addObstacle(new Brick(315,249,g));
		area.addObstacle(new Brick(551,249,g));
		area.addObstacle(new Brick(500,432,g));
		
		area.addEnemy(new Koopa(300,450,g));
		areas.add(area);
		
		
		
		Level level = new Level(areas, g.getGameboard());
		g.addLevel(level);
		
		g.play();
	}
	
}
