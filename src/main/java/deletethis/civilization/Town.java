package deletethis.civilization;

import java.util.ArrayList;

import deletethis.civilization.exception.PlotAlreadyOwnedException;
import deletethis.civilization.exception.PlotNotOwnedException;
import deletethis.civilization.exception.ResidentAlreadyInTownException;
import deletethis.civilization.exception.ResidentNotInTownException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Town
{	
	private String name;
	private ArrayList<Resident> residents;
	private ArrayList<Plot> plots;
	
	public Town()
	{
		this.name = null;
		this.residents = new ArrayList<Resident>();
		this.plots = new ArrayList<Plot>();
	}
	
	public Town(String name)
	{
		this.name = name;
		this.residents = new ArrayList<Resident>();
		this.plots = new ArrayList<Plot>();
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
			try
			{
				town.addResident(resident);
			}
			catch (ResidentAlreadyInTownException e)
			{
				e.printStackTrace();
			}
		}
		
		return town;
	}
	
	public boolean hasResident(Resident resident)
	{	
		return residents.contains(resident);
	}
	
	public void addResident(Resident resident) throws ResidentAlreadyInTownException
	{
		if(hasResident(resident))
			throw new ResidentAlreadyInTownException(resident, this);
		
		residents.add(resident);
	}
	
	public void removeResident(Resident resident) throws ResidentNotInTownException
	{
		if(!hasResident(resident))
			throw new ResidentNotInTownException(resident, this);
		
		residents.remove(resident);
	}
	
	public ArrayList<Resident> getResidents()
	{
		return residents;
	}
	
	public boolean hasPlot(Plot plot)
	{	
		return plots.contains(plot);
	}
	
	public void addPlot(Plot plot) throws PlotAlreadyOwnedException
	{
		if(hasPlot(plot))
			throw new PlotAlreadyOwnedException(this, plot);
		
		plots.add(plot);
	}
	
	public void removePlot(Plot plot) throws PlotNotOwnedException
	{
		if(!hasPlot(plot))
			throw new PlotNotOwnedException(this, plot);
		
		plots.remove(plot);
	}
	
	public ArrayList<Plot> getPlots()
	{
		return plots;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 21;
		hash *= 56 * name.hashCode();
		//hash *= 56 * residents.hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
			return false;
		if(!(object instanceof Town))
			return false;
		
		Town that = (Town) object;
		
		if(!this.getName().equals(that.getName())) return false;
		//if(!this.getResidents().equals(that.getResidents())) return false;
		
		return true;
	}
}
