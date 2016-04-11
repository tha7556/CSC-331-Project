package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import Items.Item;
import Character.Character;
import Character.Enemy;
import Obstacles.Obstacle;
/**
 * A single Area inside of a Level
 *
 */
public class Area 
{
	private ImageIcon background;
	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles; //Change to ArrayList<Obstacle> later
	private ArrayList<Item> items;     //Change to ArrayList<Item> later
	/**
	 * 
	 * @param background The String representation of the ImageIcon File
	 */
	public Area(String background)
	{
		enemies = new ArrayList<Enemy>();
		obstacles = new ArrayList<Obstacle>();
		items = new ArrayList<Item>();
		this.background = new ImageIcon(background);
	}
	/**
	 * Adds an Enemy to the enemies ArrayList
	 * @param enemy The Enemy to be added
	 */
	public void addEnemy(Enemy enemy)
	{
		enemies.add(enemy);
	}
	/**
	 * Adds an ArrayList of Enemies to the enemies ArrayList
	 * @param enemies ArrayList of Enemies to be added
	 */
	public void addEnemies(ArrayList<Enemy> enemies)
	{
		for(Enemy e : enemies)
		{
			this.enemies.add(e);
		}
	}
	/**
	 * Adds an Obstacle to the obstacles ArrayList
	 * @param obstacle The Obstacle to be added
	 */
	public void addObstacle(Obstacle obstacle)
	{
		obstacles.add(obstacle);
	}
	/**
	 * Adds an ArrayList of Obstacles to the obstacles ArrayList
	 * @param obstacles ArrayList of Obstacles to be added
	 */
	public void addObstacles(ArrayList<Obstacle> obstacles)
	{
		for(Obstacle e : obstacles)
		{
			this.obstacles.add(e);
		}
	}
	/**
	 * Adds an Item to the items ArrayList
	 * @param item The Item to be added
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}
	/**
	 * Adds an ArrayList of Items to the items ArrayList
	 * @param items ArrayList of Items to be added
	 */
	public void addItems(ArrayList<Item> items)
	{
		for(Item i : items)
		{
			this.items.add(i);
		}
	}
	/**
	 * 
	 * @return The ImageIcon of the background
	 */
	public ImageIcon getBackground()
	{
		return background;
	}
	/**
	 * 
	 * @return An ArrayList of all the Enemies in this Area
	 */
	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	/**
	 * 
	 * @return An ArrayList of all the Obstacles in this Area 
	 */
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacles;
	}
	/**
	 * 
	 * @return An ArrayList of all the Items in this Area
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}
	/**
	 * Is used to "kill" a {@link Character}, it actually just makes it invisible and not solid to avoid Errors
	 * @param c The {@link Character} to be removed
	 */
	public void removeCharacter(Character c)
	{
		c.setSolid(false);
		c.setVisible(false);
	}
	/**
	 * Is used to "destroy" an Obstacle, it actually just makes it invisible and not solid to avoid Errors
	 * @param o The Obstacle to be removed
	 */
	public void removeObstacle(Obstacle o)
	{
		o.setVisible(false);
		o.setSolid(false);
	}
	/**
	 * Is used to "destroy" an Item, it actually just makes it invisible and not solid to avoid Errors
	 * @param i The Item to be removed
	 */
	public void removeItem(Item i)
	{
		i.setVisible(false);
		i.setAlive(false);
	}
	/**
	 * Draws the current Area on the Gameboard
	 * @param g The Graphics Object
	 * @param comp The instance of Gameboard
	 */
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
