package deletethis.civilization.proxy;

import deletethis.civilization.item.ModItems;

public abstract class CommonProxy
{
	public void preInit()
	{
		ModItems.registerItems();
	}
	
	public void init()
	{	
	}
	
	public void postInit()
	{		
	}
}
