package deletethis.civilization;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
}
