package deletethis.civilization.util;

import deletethis.civilization.object.Plot;
import deletethis.civilization.object.Resident;
import deletethis.civilization.object.Town;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CivilizationObjectFactory
{
	public static Town createTown(String name, EntityPlayer founder)
	{
		Town town = new Town(name);
		
		String uuid = founder.getGameProfile().getId().toString();
		Resident founderResident = new Resident(uuid, town);
		town.addResident(founderResident);
		
		Plot plot = createPlot(founder.worldObj, founder.getPosition());
		plot.setTown(town);
		town.addPlot(plot);
		
		return town;
	}
	
	public static Plot createPlot(World world, BlockPos blockPos)
	{
        int x = world.getChunkFromBlockCoords(blockPos).xPosition;
        int z = world.getChunkFromBlockCoords(blockPos).zPosition;
        return new Plot(world, x, z, null);
	}
	
	public static Plot createPlot(World world, int x, int z)
	{
        return new Plot(world, x, z, null);
	}
}
