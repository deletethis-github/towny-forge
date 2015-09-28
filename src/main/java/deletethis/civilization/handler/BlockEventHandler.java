package deletethis.civilization.handler;

import deletethis.civilization.Plot;
import deletethis.civilization.Resident;
import deletethis.civilization.Town;
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
		Resident resident = new Resident(player.getGameProfile().getId().toString());
		World world = player.worldObj;
		BlockPos pos = event.pos;
		CivilizationWorldData data = CivilizationWorldData.get(world);
		
		if(!world.isRemote)
		{
			for(Town town : data.getTowns())
			{
				for(Plot plot : town.getPlots())
				{
					if(plot.isInPlot(world, pos) && !town.hasResident(resident))
					{
						//Disabled for now
						//UtilMessage.sendBlockEventWarning(player, town.getName());
						event.setCanceled(true);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void onBlockPlaced(BlockEvent.PlaceEvent event)
	{
		EntityPlayer player = event.player;
		Resident resident = new Resident(player.getGameProfile().getId().toString());
		World world = player.worldObj;
		BlockPos pos = event.pos;
		CivilizationWorldData data = CivilizationWorldData.get(world);
		
		if(!world.isRemote)
		{
			for(Town town : data.getTowns())
			{
				for(Plot plot : town.getPlots())
				{
					if(plot.isInPlot(world, pos) && !town.hasResident(resident))
					{
						//Disabled for now
						//UtilMessage.sendBlockEventWarning(player, town.getName());
						event.setCanceled(true);
					}
				}
			}
		}
	}
}
