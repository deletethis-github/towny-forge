package deletethis.civilization.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class UtilMessage
{
	public static void send(EntityPlayer player, String message, EnumChatFormatting color)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText component = new ChatComponentText(message);
			component.getChatStyle().setColor(color);
			player.addChatMessage(component);
		}	
	}
}
