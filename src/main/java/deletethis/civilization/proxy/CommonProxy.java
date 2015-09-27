package deletethis.civilization.proxy;

import deletethis.civilization.event.WorldLoadEventHandler;
import deletethis.civilization.item.ModItems;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public void preInit()
	{
		ModItems.registerItems();
		MinecraftForge.EVENT_BUS.register(new WorldLoadEventHandler());
	}
	
	public void init()
	{	
	}
	
	public void postInit()
	{		
	}
}
