package Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;

import Character.Mario;
import Game.Area;

public class Gameboard extends JComponent implements KeyListener
{

	private static final long serialVersionUID = 1L;
	private Mario mario;
	private Area area;
	private Set<Integer> activeKeys = new HashSet<>();
	public Gameboard(Mario mario, Area area)
	{
		super();
		this.mario = mario;
		this.area = area;
		addKeyListener(this);
		setFocusable(true);
	}
	public void paintComponent(Graphics g)
	{
		if(area != null)
		{
			area.draw(g, this);
			mario.render(g, this);
		}
	}
	public Mario getMario()
	{
		return mario;
	}
	public Set<Integer> getActiveKeys()
	{
		return activeKeys;
	}
	@Override
	public void keyPressed(KeyEvent key) 
	{
		if(!activeKeys.contains(key.getKeyCode()))
		{
			activeKeys.add(key.getKeyCode());
		}
	}
	@Override
	public void keyReleased(KeyEvent key)
	{	
		activeKeys.remove(key.getKeyCode());			
	}
	@Override
	public void keyTyped(KeyEvent key) 
	{
		
	}
	
}
