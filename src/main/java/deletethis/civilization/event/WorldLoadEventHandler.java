package deletethis.civilization.event;

import deletethis.civilization.world.CivilizationWorldData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldLoadEventHandler
{
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		CivilizationWorldData data = (CivilizationWorldData)event.world.loadItemData(CivilizationWorldData.class, CivilizationWorldData.IDENTIFIER);
		if(data == null)
		{
			data = new CivilizationWorldData(CivilizationWorldData.IDENTIFIER);
			event.world.setItemData(CivilizationWorldData.IDENTIFIER, data);
		}
	}
}
