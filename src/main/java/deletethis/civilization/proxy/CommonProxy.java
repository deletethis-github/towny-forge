package deletethis.civilization.proxy;

import deletethis.civilization.handler.BlockEventHandler;
import deletethis.civilization.item.ModItems;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public void preInit()
	{
		ModItems.registerItems();
		MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
	}
	
	public void init()
	{	
	}
	
	public void postInit()
	{		
	}
}
