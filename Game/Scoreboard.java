package Game;

import java.awt.Graphics;

public class Scoreboard 
{
	private int lives;
	private int score;
	public Scoreboard()
	{
		score = 0;
		lives = 5;
	}
	public int getLives()
	{
		return lives;
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public void addPoints(int points)
	{
		score += points;
	}
	public void draw(Graphics g)
	{
		//Draw itself
	}
}
