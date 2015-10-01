package deletethis.civilization;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CivilizationObjectFactory
{
	public static Town createTown(String name, EntityPlayer founder)
	{
		Town town = new Town(name);
		
		String uuid = founder.getGameProfile().getId().toString();
		Resident founderResident = new Resident(uuid);
		town.addResident(founderResident);
		
		Plot plot = createPlot(founder.worldObj, founder.getPosition());
		town.addPlot(plot);
		
		return town;
	}
	
	public static Plot createPlot(World world, BlockPos blockPos)
	{
        int dimension = world.provider.getDimensionId();
        int x = world.getChunkFromBlockCoords(blockPos).xPosition;
        int z = world.getChunkFromBlockCoords(blockPos).zPosition;
        return new Plot(dimension, x, z);
	}
}
