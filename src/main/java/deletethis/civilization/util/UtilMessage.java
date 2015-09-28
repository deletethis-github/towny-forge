package deletethis.civilization.util;

import deletethis.civilization.chat.ChatComponentWarning;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class UtilMessage
{
	public static void sendSimpleMessage(EntityPlayer player, String message, EnumChatFormatting color)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText component = new ChatComponentText(message);
			component.getChatStyle().setColor(color);
			player.addChatMessage(component);
		}	
	}
	
	public static void sendCantBreakBlockMessage(EntityPlayer player)
	{
		player.addChatMessage(new ChatComponentWarning("You can't break that block!"));
	}
}
