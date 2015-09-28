package deletethis.civilization.proxy;

import deletethis.civilization.handler.BlockBreakEventHandler;
import deletethis.civilization.item.ModItems;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public void preInit()
	{
		ModItems.registerItems();
		MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());
	}
	
	public void init()
	{	
	}
	
	public void postInit()
	{		
	}
}
