package deletethis.civilization.event;

import deletethis.civilization.CivilizationWorldData;
import deletethis.civilization.ModCivilization;
import deletethis.civilization.Resident;
import deletethis.civilization.Town;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldLoadEventHandler
{
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		ModCivilization.instance.getTownManager().clear();
		
		Town townTest = new Town("Test");
		Resident deletethis = new Resident("f5fb0f5d-cfb6-418f-ae4b-c53e1539d6ba");
		townTest.addResident(deletethis);
		ModCivilization.instance.getTownManager().addTown(townTest);
		
		CivilizationWorldData.getForWorld(event.world).markDirty();
	}
}
