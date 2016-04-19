package Game;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;

import Characters.Mario;
import Game.Area;
/**
 * Extends JComponent and implements KeyListener.
 * <br>When a key is pressed it is added to the Set activeKeys
 * <br>When it is released it is removed
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
		Area area = level.getCurrentArea();
		if(area != null)
		{
			area.draw(g, this);
			mario.render(g, this);
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
