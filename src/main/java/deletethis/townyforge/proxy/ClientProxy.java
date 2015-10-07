package deletethis.townyforge.proxy;

import deletethis.townyforge.item.ModItems;

public class ClientProxy extends CommonProxy
{
	public void preInit()
	{
		super.preInit();
		ModItems.addVariantNames();
	}
	
	public void init()
	{	
		super.init();
		ModItems.registerItemModels();
	}
	
	public void postInit()
	{		
		super.postInit();
	}
}
