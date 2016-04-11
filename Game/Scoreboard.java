package Game;

import java.awt.Graphics;
/**
 * The Scoreboard that displays Mario's lives, the Time, and the player's points
 *
 */
public class Scoreboard 
{
	private int lives;
	private int score;
	/**
	 * Creates a new Scoreboard with score at 0 and lives at 5
	 */
	public Scoreboard()
	{
		score = 0;
		lives = 5;
	}
	/**
	 * 
	 * @return The current number of Mario's lives
	 */
	public int getLives()
	{
		return lives;
	}
	/**
	 * 
	 * @return The player's current score
	 */
	public int getScore()
	{
		return score;
	}
	/**
	 * 
	 * @param score The new Score
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
	/**
	 * 
	 * @param points The amount of points to be added
	 */
	public void addPoints(int points)
	{
		score += points;
	}
	/**
	 * Displays the Scoreboard
	 * @param g The Graphics object
	 */
	public void draw(Graphics g)
	{
		//Draw itself
	}
}
