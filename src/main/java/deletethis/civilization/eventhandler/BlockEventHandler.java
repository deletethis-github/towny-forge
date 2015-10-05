package deletethis.civilization.eventhandler;

import deletethis.civilization.object.Plot;
import deletethis.civilization.object.Town;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void onBlockBreak(BlockEvent.BreakEvent event)
	{
		EntityPlayer player = event.getPlayer();
		World world = player.worldObj;
		BlockPos pos = event.pos;
		CivilizationWorldData data = CivilizationWorldData.get(world);
		
		if(!world.isRemote)
		{
			for(Town town : data.getTowns())
			{
				for(Plot plot : town.getPlots())
				{
					if(plot.isInPlot(world, pos))
					{
						if (town.hasResident(player.getGameProfile().getId().toString())) break;
						event.setCanceled(true);
						break;
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void onBlockPlaced(BlockEvent.PlaceEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.worldObj;
		BlockPos pos = event.pos;
		CivilizationWorldData data = CivilizationWorldData.get(world);
		
		if(!world.isRemote)
		{
			for(Town town : data.getTowns())
			{
				for(Plot plot : town.getPlots())
				{
					if(plot.isInPlot(world, pos))
					{
						if (town.hasResident(player.getGameProfile().getId().toString())) break;
						event.setCanceled(true);
						break;
					}
				}
			}
		}
	}
}
