package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Characters.BulletBill;
import Characters.Character;
import Characters.Enemy;
import Characters.Koopa;
import Characters.PiranhaPlant;
import Characters.Thwomp;
import Items.Coin;
import Items.Item;
import Obstacles.*;
/**
 * A single Area inside of a Level
 * @author Tyler And Jacob
 *
 */
public class Area 
{
	private String backGroundName;
	private ImageIcon displayBackground;
	private BufferedImage background;
	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles; //Change to ArrayList<Obstacle> later
	private ArrayList<Item> items;     //Change to ArrayList<Item> later
	private int x,y;
	/**
	 * 
	 * @param background The String representation of the ImageIcon File
	 */
	public Area(String background)
	{
		backGroundName = background;
		enemies = new ArrayList<Enemy>();
		obstacles = new ArrayList<Obstacle>();
		items = new ArrayList<Item>();
		this.displayBackground = new ImageIcon("Images\\LevelBackground.png");
		File fileName = new File(background);
		 try {
		 this.background = ImageIO.read(fileName);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
	}
	/**
	 * 
	 * @return background file name as a string for comparison
	 */
	public String returnBackGround(){
		return backGroundName;
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
	public ImageIcon getDisplayBackground()
	{
		return displayBackground;
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
			displayBackground.paintIcon(comp, g, 0, 0);
			for(Enemy e : enemies)
				if(e.isVisible())
					e.render(g, comp);
			for(Obstacle o : obstacles)
				if(o.isVisible())
				{
					o.render(g, comp);
					if(o instanceof QuestionBlock)
					{
						QuestionBlock q = (QuestionBlock)o;
						if(q.getItem().isVisible() & q.getItem().isAlive())
							q.getItem().render(g, comp);
					}
				}

			for(Item i : items){
				if(i.isVisible()){
					i.render(g, comp);
				}
			}
		}
	}
	/**
	 * Adds Objects to Area using 30x20 image
	 * <br>Black = Brick
	 * <br>Red = Koopa
	 * <br>Green = Question Block
	 * <br>Blue = Pipe
	 * <br>Yellow = Ground
	 * <br>Purple = FinishLine
	 * @param area The Area to add Objects to
	 * @param g The instance of the Game
	 */
	 public void createArea(Area area,Game g){
	 		int width = 30;
	 		int height = 20;
	 		
	 		
	 		for(int y=0; y < height; y++){
	 			for(int x=0; x < width; x++){
	 				int pixel = background.getRGB(x, y);
	 				
	 				int red = (pixel >> 16) & 0xff;
	 				int green = (pixel >> 8) & 0xff;
	 				int blue = (pixel) & 0xff;
	 				
	 				if(red == 30 && green == 40 && blue == 100){
	 					area.addObstacle(new StartGameBlock(x*32, y*32-32,g));
	 				}
	 				if(red ==100 && green ==100 && blue ==200){
	 					area.addObstacle(new EndGameBlock(x*32,y*32-32,g));
	 				}
	 				if(red == 150 && green == 150 && blue == 150){
	 					area.addObstacle(new RestartGameBlock(x*32,y*32-32,g));
	 				}
	 				if(red == 0 && green == 0 && blue == 0){
	 					area.addObstacle(new Brick(x*32,y*32-32,g));
	 
	 				}
	 				if(red == 255 && green == 0 && blue == 0){
	 					area.addEnemy(new Koopa(x*32,y*32-32,g));
	 
	 				}
	 				if(red == 127 && green == 0 && blue == 0){
	 					area.addEnemy(new BulletBill(x*32,y*32-32,g));
	 
	 				}
	 				if(red == 0 && green == 127 && blue == 0){
	 					area.addEnemy(new PiranhaPlant(x*32,y*32-64,g));
	 
	 				}
	 				if(red == 0 && green == 0 && blue == 127){
	 					area.addEnemy(new Thwomp(x*32,y*32-32,g));
	 
	 				}
	 				
	 				if(red == 0 && green == 255 && blue == 0){
	 					area.addObstacle(new QuestionBlock(x*32,y*32-32,g));
	 
	 				}
	 				if(red == 0 && green == 0 && blue == 255)
	 				{
	 					area.addObstacle(new Pipe(x*32, y*32-64, g));
	 				}
	 				
	 				if(red == 1 && green == 34 && blue == 102){
	 					area.addEnemy(new PiranhaPlant(x*32+16,y*32-64,g));
	 					area.addObstacle(new Pipe(x*32, y*32-64, g));
	 					
	 				}
	 				if(red == 255 && green == 255 && blue == 0)
	 				{
	 					area.addObstacle(new Ground(x*32, y*32-32,g));
	 				}
	 				if(red == 255 && green == 0 && blue == 255)
	 				{
	 					area.addObstacle(new FinishLine(x*32,y*32-375,g));
	 				}
	 				if(red==0 && green == 255 && blue == 255)
	 					area.addItem(new Coin(x*32,y*32-32,g));
	 				if(red==5 && green == 5 && blue == 5)
	 				{
	 					setRespawnPoint(x*32+5, y*32-g.getMario().getHeight()-5);	
	 				}
	 			}
	 		}
	 }
	 /**
	  * Sets the point that Mario will respawn at after he dies
	  * @param x The x coordinate where Mario will go
	  * @param y The y coordinate where Mario will go
	  */
	 public void setRespawnPoint(int x, int y)
	 {
		 this.x = x;
		 this.y = y;
	 }
	 /**
	  * 
	  * @return The x coordinate of the spawn point
	  */
	 public int getRespawnPointX()
	 {
		 return x;
	 }
	 /**
	  * 
	  * @return The y coordinate of the spawn point
	  */
	 public int getRespawnPointY()
	 {
		 return y;
	 }
}

