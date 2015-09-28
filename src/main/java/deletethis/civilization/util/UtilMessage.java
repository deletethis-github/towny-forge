package deletethis.civilization.util;

import deletethis.civilization.chat.ChatComponentTown;
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
	
	public static void sendBlockEventWarning(EntityPlayer player, String townName)
	{
		ChatComponentText message = new ChatComponentText("");
		ChatComponentWarning one = new ChatComponentWarning("That block lies within a plot that is owned by ");
		ChatComponentTown town = new ChatComponentTown(townName);
		ChatComponentWarning end = new ChatComponentWarning("!");
		
		message.appendSibling(one);
		message.appendSibling(town);
		message.appendSibling(end);
		
		player.addChatMessage(message);
	}
}
