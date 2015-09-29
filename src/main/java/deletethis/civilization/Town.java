package deletethis.civilization;

import java.util.ArrayList;

import deletethis.civilization.exception.PlotAlreadyHasOwnerException;
import deletethis.civilization.exception.PlotAlreadyRegisteredException;
import deletethis.civilization.exception.PlotNotRegisteredException;
import deletethis.civilization.exception.ResidentAlreadyRegisteredException;
import deletethis.civilization.exception.ResidentNotRegisteredException;
import deletethis.civilization.exception.TownAlreadyExistsException;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

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
			try
			{
				town.addResident(resident);
			}
			catch (ResidentAlreadyRegisteredException e)
			{
			}
		}
		
		NBTTagList tagListPlots = nbt.getTagList("plots", 10);
		for(int i = 0; i < tagListPlots.tagCount(); i++)
		{
			NBTTagCompound plotsIterator = (NBTTagCompound)tagListPlots.get(i);
			Plot plot = Plot.readFromNBT(plotsIterator);
			try {town.addPlot(plot);}
			catch (PlotAlreadyRegisteredException e) {}
		}
		
		return town;
	}
	
	public boolean hasResident(Resident resident)
	{	
		return residents.contains(resident);
	}
	
	public void addResident(Resident resident) throws ResidentAlreadyRegisteredException
	{
		if(hasResident(resident))
			throw new ResidentAlreadyRegisteredException(resident, this);
		
		residents.add(resident);
	}
	
	public void removeResident(Resident resident) throws ResidentNotRegisteredException
	{
		if(!hasResident(resident))
			throw new ResidentNotRegisteredException(resident, this);
		
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
	
	public void addPlot(Plot plot) throws PlotAlreadyRegisteredException
	{
		if(hasPlot(plot))
			throw new PlotAlreadyRegisteredException(this, plot);
		
		plots.add(plot);
	}
	
	//Having to pass the ArrayList of Towns is necessary because we get a stack overflow error 
	//if we call CivilizationWorldData.get() within addPlot()
	//I guess because we call addPlot() when reading the world data NBT?
	//which is done when calling CivilizationWorldData.get()
	public void addPlot(Plot plot, ArrayList<Town> towns) throws PlotAlreadyRegisteredException, PlotAlreadyHasOwnerException
	{
		if(hasPlot(plot))
			throw new PlotAlreadyRegisteredException(this, plot);
		
		for(Town t : towns)
		{
			if(t.hasPlot(plot))
				throw new PlotAlreadyHasOwnerException(t, plot);
		}	
		
		plots.add(plot);
	}
	
	public void removePlot(Plot plot) throws PlotNotRegisteredException
	{
		if(!hasPlot(plot))
			throw new PlotNotRegisteredException(this, plot);
		
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
	
	public static void create(String name, World world, EntityPlayer founder) throws TownAlreadyExistsException, PlotAlreadyHasOwnerException
	{
        CivilizationWorldData data = CivilizationWorldData.get(world);
        Town town = new Town(name);
        // ATTEMPT TO ADD THE FOUNDER AS A RESIDENT TO THE TOWN
        String uuid = founder.getGameProfile().getId().toString();
        Resident resident = new Resident(uuid);
		try {town.addResident(resident);} catch (ResidentAlreadyRegisteredException e) {}
        // ATTEMPT TO ADD THE FOUNDING PLOT TO THE TOWN
        int dimension = world.provider.getDimensionId();
        int x = world.getChunkFromBlockCoords(founder.getPosition()).xPosition;
        int z = world.getChunkFromBlockCoords(founder.getPosition()).zPosition;
        Plot plot = new Plot(dimension, x, z);
		for(Town t : data.getTowns())
		{
			if(t.hasPlot(plot))
				throw new PlotAlreadyHasOwnerException(t, plot);
		}	
		try {town.addPlot(plot);} catch (PlotAlreadyRegisteredException e) {}
        // ATTEMPT TO ADD THE TOWN TO WORLD DATA
        try {data.addTown(town);}
		catch (TownAlreadyExistsException e) 
        {
			throw new TownAlreadyExistsException(town);
		}
	}
}
