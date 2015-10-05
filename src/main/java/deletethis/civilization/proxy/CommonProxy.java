package deletethis.civilization.proxy;

import deletethis.civilization.eventhandler.AttackEntityEventHandler;
import deletethis.civilization.eventhandler.BlockEventHandler;
import deletethis.civilization.eventhandler.ItemDestructionEventHandler;
import deletethis.civilization.eventhandler.LivingHurtEventHandler;
import deletethis.civilization.eventhandler.PlayerInteractEventHandler;
import deletethis.civilization.item.ModItems;
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
