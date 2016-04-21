package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;

import Characters.Mario;
import Game.Area;
/**
 * Extends JComponent and implements KeyListener.
 * <br>When a key is pressed it is added to the Set activeKeys
 * <br>When it is released it is removed
 * @author Tyler & Jacob
 *
 */
public class Gameboard extends JComponent implements KeyListener
{

	private static final long serialVersionUID = 1L;
	private Mario mario;
	private Level level;
	private Set<Integer> activeKeys = new HashSet<>();
	/**
	 * 
	 * @param mario The Mario of the Game
	 * @param level The Level to be displayed
	 */
	public Gameboard(Mario mario, Level level)
	{
		super();
		this.mario = mario;
		this.level = level;
		addKeyListener(this);
		setFocusable(true);
		this.setPreferredSize(new Dimension(960,640));
	}
	@Override
	public void paintComponent(Graphics g)
	{
		if(!mario.isEnding())
		{
			Area area = level.getCurrentArea();
			if(area != null)
			{
				area.draw(g, this);
				mario.render(g, this);
			}
		}
		else
		{
			Rectangle r = new Rectangle(0,0,getWidth(),getHeight());
			Ellipse2D.Double e;
			if(!mario.isBig())
				 e = new Ellipse2D.Double(mario.getX()-mario.getWidth()+15, mario.getY()-mario.getHeight()+20, mario.getWidth()*2, mario.getHeight()*2);
			else
				e = new Ellipse2D.Double(mario.getX()-mario.getWidth()+15, mario.getY()-mario.getHeight()+20, mario.getWidth()*4, mario.getHeight()*2);
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(Color.BLACK);
			g2.fill(r);
			g2.setColor(new Color(148,146,255));
			g2.fill(e);
			mario.render(g2, this);
			
		}
	}
	/**
	 * 
	 * @return The Mario being drawn
	 */
	public Mario getMario()
	{
		return mario;
	}
	/**
	 * 
	 * @return The activeKeys Set containing all Keys currently pressed
	 */
	public Set<Integer> getActiveKeys()
	{
		return activeKeys;
	}
	/**
	 * When a Key is pressed it is added to the activeKeys Set
	 */
	@Override
	public void keyPressed(KeyEvent key) 
	{
		if(!activeKeys.contains(key.getKeyCode()))
		{
			activeKeys.add(key.getKeyCode());
		}
	}
	/**
	 * When a Key is released it is removed from the activeKeys Set
	 */
	@Override
	public void keyReleased(KeyEvent key)
	{	
		activeKeys.remove(key.getKeyCode());			
	}
	/**
	 * Not used
	 */
	@Override
	public void keyTyped(KeyEvent key) 
	{
		
	}
	
}
