package deletethis.civilization.eventhandler;

import deletethis.civilization.object.Town;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AttackEntityEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void onAttackEntity(AttackEntityEvent event)
	{	
		Entity target = event.target;
		if(!(target instanceof EntityAnimal || target instanceof EntityVillager)) return;
		
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		BlockPos blockPos = target.getPosition();
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
