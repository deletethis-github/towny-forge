package deletethis.civilization.world;

import java.util.ArrayList;

import deletethis.civilization.Town;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class CivilizationWorldData extends WorldSavedData
{
	public static final String IDENTIFIER = "civilization";
	
	private ArrayList<Town> towns;
	
	public CivilizationWorldData(String identifier)
	{
		super(identifier);
		towns = new ArrayList<Town>();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{	
		NBTTagList tagListTowns = nbt.getTagList("towns", 10);
		for(int i = 0; i < tagListTowns.tagCount(); i++)
		{
			NBTTagCompound townsIterator = (NBTTagCompound)tagListTowns.get(i);
			Town town = Town.readFromNBT(townsIterator);
			this.addTown(town);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{			
		NBTTagList tagListTowns = new NBTTagList();
		for(Town townsIterator : this.getTowns())
		{
			NBTTagCompound tagTown = new NBTTagCompound();
			townsIterator.writeToNBT(tagTown);	
			tagListTowns.appendTag(tagTown);
		}	
		nbt.setTag("towns", tagListTowns);
	}
	
	public Town getTown(String name)
	{
		Town dummy = new Town(name);
		for(Town town : towns)
		{
			if(town.equals(dummy))
			{
				return town;
			}
		}
		return null;
	}
	
	public boolean townExists(String name)
	{
		Town dummy = new Town(name);
		return towns.contains(dummy);
	}
	
	public boolean townExists(Town town)
	{
		return towns.contains(town);
	}
	
	public void addTown(Town town)
	{
		towns.add(town);
		this.markDirty();
	}
	
	public void removeTown(Town town)
	{
		towns.remove(town);
		this.markDirty();
	}
	
	public ArrayList<Town> getTowns()
	{
		return towns;
	}
	
	public static CivilizationWorldData get(World world)
	{
		CivilizationWorldData data = (CivilizationWorldData)world.loadItemData(CivilizationWorldData.class, CivilizationWorldData.IDENTIFIER);
		if(data == null)
		{
			data = new CivilizationWorldData(CivilizationWorldData.IDENTIFIER);
			world.setItemData(CivilizationWorldData.IDENTIFIER, data);
		}
		return data;
	}
}
