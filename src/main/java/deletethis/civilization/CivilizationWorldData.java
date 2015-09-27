package deletethis.civilization;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class CivilizationWorldData extends WorldSavedData
{
	private static final String IDENTIFIER = "civilization";
	
	public CivilizationWorldData(String identifier)
	{
		super(identifier);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{	
		TownManager townManager = ModCivilization.instance.getTownManager();
		NBTTagList tagListTowns = nbt.getTagList("towns", 10);
		for(int i = 0; i < tagListTowns.tagCount(); i++)
		{
			NBTTagCompound townsIterator = (NBTTagCompound)tagListTowns.get(i);
			Town town = Town.readFromNBT(townsIterator);
			townManager.addTown(town);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{	
		TownManager townManager = ModCivilization.instance.getTownManager();
		
		NBTTagList tagListTowns = new NBTTagList();
		for(Town townsIterator : townManager.getTowns())
		{
			NBTTagCompound tagTown = new NBTTagCompound();
			townsIterator.writeToNBT(tagTown);	
			tagListTowns.appendTag(tagTown);
		}	
		nbt.setTag("towns", tagListTowns);
	}
	
	public static CivilizationWorldData getForWorld(World world)
	{
		CivilizationWorldData data = (CivilizationWorldData)world.loadItemData(CivilizationWorldData.class, IDENTIFIER);
		if(data == null)
		{
			data = new CivilizationWorldData(IDENTIFIER);
			world.setItemData(IDENTIFIER, data);
		}
		return data;
	}
}
