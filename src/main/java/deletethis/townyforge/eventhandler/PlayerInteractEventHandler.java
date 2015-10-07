package deletethis.townyforge.eventhandler;

import deletethis.townyforge.object.Town;
import deletethis.townyforge.world.CivilizationWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerInteractEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) return;
		
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		BlockPos blockPos = event.pos;
		CivilizationWorldData data = CivilizationWorldData.get(world);
		
		if(!world.isRemote)
		{
			for(Town town : data.getTowns())
			{
				if(town.isInTown(world, blockPos))
				{
					if (town.hasResident(player.getGameProfile().getId().toString())) break;
					event.setCanceled(true);
					break;
				}
			}
		}
	}
}
