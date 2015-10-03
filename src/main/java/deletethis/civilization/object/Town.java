package deletethis.civilization.object;

import java.util.ArrayList;

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
		
		NBTTagList tagListPlots = new NBTTagList();
		for(Plot plotsIterator : this.getPlots())
		{
			NBTTagCompound tagPlot = new NBTTagCompound();
			plotsIterator.writeToNBT(tagPlot);
			tagListPlots.appendTag(tagPlot);
		}
		nbt.setTag("plots", tagListPlots);
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
		
		NBTTagList tagListPlots = nbt.getTagList("plots", 10);
		for(int i = 0; i < tagListPlots.tagCount(); i++)
		{
			NBTTagCompound plotsIterator = (NBTTagCompound)tagListPlots.get(i);
			Plot plot = Plot.readFromNBT(plotsIterator);
			town.addPlot(plot);
		}
		
		return town;
	}
	
	public boolean hasResident(Resident resident)
	{	
		return residents.contains(resident);
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
	
	public boolean hasPlot(Plot plot)
	{	
		return plots.contains(plot);
	}
	
	public void addPlot(Plot plot)
	{
		plots.add(plot);
	}
	
	public void removePlot(Plot plot)
	{
		plots.remove(plot);
	}
	
	public ArrayList<Plot> getPlots()
	{
		return plots;
	}
	
	public int getPlotCount()
	{
		return plots.size();
	}
	
	@Override
	public int hashCode()
	{
		int hash = 21;
		hash *= 56 + name.hashCode();
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
		
		return true;
	}
}
