package deletethis.townyforge.event.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.item.ItemEvent;

public class ItemDestructionEvent extends ItemEvent
{
	public ItemDestructionEvent(EntityItem itemEntity)
	{
		super(itemEntity);
	}
}
