package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
/**
 * The Scoreboard that displays Mario's lives, the Time, and the player's points
 * <br>Extends JComponent</br>
 *
 */
public class Scoreboard extends JComponent
{
	private static final long serialVersionUID = 1L;
	private int lives;
	private int score;
	private int coins;
	private ImageIcon scoreboardimage;
	private ImageIcon titleImage;
	private Game game;
	private boolean visibility;

	/**
	 * Creates a new Scoreboard with score at 0 and lives at 5
	 */
	public Scoreboard(Game game)
	{
		super();
		setPreferredSize(new Dimension(960,50));
		score = 0;
		coins = 0;
		lives = 5;
		scoreboardimage = new ImageIcon("Images\\ScoreBoard.png");
		titleImage = new ImageIcon("Images\\titleImage.png");
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		if(game.getCurrentLevel().getCurrentIndex() == 0){
			lives = 5;
			score = 0;
			game.resetActualTime();
			setVisibility(false);
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 960, 50);
		}
		else if(game.getCurrentLevel().getCurrentIndex() == 1){
			setVisibility(false);
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, 960, 50);
			
		}
		else if(game.getCurrentLevel().getCurrentIndex() > 1){
			setVisibility(true);
			String displayScore = String.format("%08d", score);
			scoreboardimage.paintIcon(this, g, 0, 0);
			g.setFont(new Font("Dialogue",Font.BOLD,20));
			g.drawString(lives + "", 95, 47);
			g.drawString(coins + "", 460, 25);
			g.drawString(displayScore + "", 410, 45);
			g.drawString(game.getDisplayTime()+"", 830, 30);
		}
		
		
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
	 * Adds 1 coin to Mario and rewards him with 100 points
	 */
	public void addCoin()
	{
		coins++;
		score += 100;
	}
	public void addScore(){
		score+=500;
	}
	/**
	 * Gives Mario an extra life
	 */
	public void addLife()
	{
		this.lives ++;
	}
	/**
	 * Takes a life from Mario
	 */
	public void takeLife()
	{
		this.lives--;
	}
	/**
	 * Resets the score, and coins back to 0
	 */
	public void reset()
	{
		coins = 0;
		score = 0;
	}
	
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
}
