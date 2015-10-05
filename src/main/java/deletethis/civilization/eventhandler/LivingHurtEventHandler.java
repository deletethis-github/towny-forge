package deletethis.civilization.eventhandler;

import deletethis.civilization.object.Town;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingHurtEventHandler
{
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void onLivingHurt(LivingHurtEvent event)
	{	
		Entity entitySource = event.source.getEntity();
		if(!(entitySource instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) entitySource;
		
		EntityLivingBase entity = event.entityLiving;
		World world = entity.worldObj;
		BlockPos blockPos = entity.getPosition();
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
