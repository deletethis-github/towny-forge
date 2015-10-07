package deletethis.townyforge.proxy;

import deletethis.townyforge.eventhandler.AttackEntityEventHandler;
import deletethis.townyforge.eventhandler.BlockEventHandler;
import deletethis.townyforge.eventhandler.ItemDestructionEventHandler;
import deletethis.townyforge.eventhandler.LivingHurtEventHandler;
import deletethis.townyforge.eventhandler.PlayerInteractEventHandler;
import deletethis.townyforge.item.ModItems;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public void preInit()
	{
		ModItems.registerItems();
		MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
		MinecraftForge.EVENT_BUS.register(new ItemDestructionEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerInteractEventHandler());
		MinecraftForge.EVENT_BUS.register(new AttackEntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new LivingHurtEventHandler());
	}
	
	public void init()
	{	
	}
	
	public void postInit()
	{		
	}
}
