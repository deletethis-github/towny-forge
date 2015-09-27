package deletethis.civilization;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Town
{
	private String name;
	private ArrayList<Resident> residents;
	
	public Town()
	{
		this.name = null;
		this.residents = new ArrayList<Resident>();
	}
	
	public Town(String name)
	{
		this.name = name;
		this.residents = new ArrayList<Resident>();
	}
	
	public Town(String name, ArrayList<Resident> residents)
	{
		this.name = name;
		this.residents = residents;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setString("name", name);
		NBTTagList tagListResidents = new NBTTagList();
		for(Resident residentsIterator : this.getResidents())
		{
			NBTTagCompound tagResident = new NBTTagCompound();
			residentsIterator.writeToNBT(tagResident);
			tagListResidents.appendTag(tagResident);
		}
		nbt.setTag("residents", tagListResidents);
	}

	public static Town readFromNBT(NBTTagCompound nbt)
	{
		String name = nbt.getString("name");
		Town town = new Town(name);
	
		NBTTagList tagListResidents = nbt.getTagList("residents", 10);
		for(int i = 0; i < tagListResidents.tagCount(); i++)
		{
			NBTTagCompound residentsIterator = (NBTTagCompound)tagListResidents.get(i);
			Resident resident = Resident.readFromNBT(residentsIterator);
			town.addResident(resident);
		}
		
		return town;
	}
	
	public boolean hasResident(Resident resident)
	{
		for(Resident i : residents)
		{
			if(i.getUUID() == resident.getUUID())
			{
				return true;
			}
		}
		return false;
	}
	
	public void addResident(Resident resident)
	{
		residents.add(resident);
	}
	
	public void removeResident(Resident resident)
	{
		residents.remove(resident);
	}
	
	public ArrayList<Resident> getResidents()
	{
		return residents;
	}
}
