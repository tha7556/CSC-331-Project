package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import Items.Item;
import Character.Character;
import Character.Enemy;
import Obstacles.Obstacle;

public class Area 
{
	private ImageIcon background;
	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles; //Change to ArrayList<Obstacle> later
	private ArrayList<Item> items;     //Change to ArrayList<Item> later
	
	public Area(String background)
	{
		enemies = new ArrayList<Enemy>();
		obstacles = new ArrayList<Obstacle>();
		items = new ArrayList<Item>();
		this.background = new ImageIcon(background);
	}
	public void addEnemy(Enemy enemy)
	{
		enemies.add(enemy);
	}
	public void addEnemies(ArrayList<Enemy> enemy)
	{
		for(Enemy e : enemy)
		{
			enemies.add(e);
		}
	}
	public void addObstacle(Obstacle obstacle)
	{
		obstacles.add(obstacle);
	}
	public void addObstacles(ArrayList<Obstacle> obstacle)
	{
		for(Obstacle e : obstacle)
		{
			obstacles.add(e);
		}
	}
	public void addItem(Item item)
	{
		items.add(item);
	}
	public void addItems(ArrayList<Item> item)
	{
		for(Item i : item)
		{
			items.add(i);
		}
	}
	public ImageIcon getBackground()
	{
		return background;
	}
	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacles;
	}
	public ArrayList<Item> getItems()
	{
		return items;
	}
	public void removeCharacter(Character c)
	{
		c.setSolid(false);
		c.setVisible(false);
	}
	public void removeObstacle(Obstacle o)
	{
		o.setVisible(false);
		o.setSolid(false);
	}
	public void draw(Graphics g, Gameboard comp)
	{
		if(g != null && comp != null)
		{
			background.paintIcon(comp, g, 0, 0);
			for(Obstacle o : obstacles)
				if(o.isVisible())
					o.render(g, comp);
			for(Enemy e : enemies)
				if(e.isAlive())
					e.render(g, comp);
		}
	}
	
}
