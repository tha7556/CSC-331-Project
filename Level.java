package Game;
import java.awt.Graphics;
import java.util.ArrayList;

public class Level 
{
	private ArrayList<Area> areas;
	private Area currentArea;
	private Gameboard gameboard;
	
	public Level(ArrayList<Area> areas, Gameboard gameboard)
	{
		this.areas = areas;
		this.gameboard = gameboard;
		if(areas.size() > 0)
		{
			this.currentArea = areas.get(0);
			displayArea(currentArea,gameboard.getGraphics(), gameboard);
		}
	}
	public void addArea(Area area)
	{
		this.areas.add(area);
	}
	public ArrayList<Area> getAreas()
	{
		return areas;
	}
	public Area getCurrentArea()
	{
		return currentArea;
	}
	public void setCurrentArea(int num)
	{
		currentArea = areas.get(num);
	}
	public void displayArea(Area area, Graphics g, Gameboard comp)
	{
		if(area != null)
		{
			area.draw(g, comp);
		}
	}
	public Gameboard getGameboard()
	{
		return gameboard;
	}
}
