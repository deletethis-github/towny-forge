package deletethis.civilization;

import java.util.ArrayList;

public class TownManager
{
	private ArrayList<Town> towns;
	
	public TownManager()
	{
		towns = new ArrayList<Town>();
	}
	
	public boolean townExists(Town town)
	{
		for(Town i : towns)
		{
			if(i.getName() == town.getName())
			{
				return true;
			}
		}
		return false;
	}
	
	public void addTown(Town town)
	{
		towns.add(town);
	}
	
	public void removeTown(Town town)
	{
		towns.remove(town);
	}
	
	public ArrayList<Town> getTowns()
	{
		return towns;
	}
	
	public void clear()
	{
		towns = new ArrayList<Town>();
	}
}
