package deletethis.civilization.event;

import deletethis.civilization.Resident;
import deletethis.civilization.Town;
import deletethis.civilization.exception.ResidentAlreadyInTownException;
import deletethis.civilization.exception.TownAlreadyExistsException;
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
			Town town = new Town("Rome");
			Resident deletethis = new Resident("f5fb0f5d-cfb6-418f-ae4b-c53e1539d6ba");
			try
			{
				town.addResident(deletethis);
			}
			catch (ResidentAlreadyInTownException e)
			{
				e.printStackTrace();
			}
			try
			{
				data.addTown(town);
			}
			catch (TownAlreadyExistsException e)
			{
				e.printStackTrace();
			}
		}
	}
}
