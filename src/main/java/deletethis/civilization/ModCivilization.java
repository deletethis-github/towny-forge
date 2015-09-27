package deletethis.civilization;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class ModCivilization
{
	@Mod.Instance
	public static ModCivilization instance;
	
	private TownManager townManager;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	townManager = new TownManager();
    }
    
    public TownManager getTownManager()
    {
    	return townManager;
    }
}
