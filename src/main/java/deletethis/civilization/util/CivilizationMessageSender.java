package deletethis.civilization.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CivilizationMessageSender
{
	public static void send(EntityPlayer player, EnumMessage type, String ... info)
	{
		if(player.worldObj.isRemote)
			return;
		
		ChatComponentText message = new ChatComponentText("");
		ChatStyle style = message.getChatStyle();
		
		switch(type)
		{
			case TOWN_CREATED:
			{
				message.appendText("The town of ");
				ChatComponentText townName = new ChatComponentText(info[0]);
				townName.getChatStyle().setItalic(true);
				message.appendSibling(townName);
				message.appendText(" has been created!");
				style.setColor(EnumChatFormatting.AQUA);
			}
			case TOWN_ALREADY_EXISTS:
			{
				message.appendText("A town with the name of ");
				ChatComponentText townName = new ChatComponentText(info[0]);
				townName.getChatStyle().setItalic(true);
				message.appendSibling(townName);
				message.appendText(" already exists!");
				style.setColor(EnumChatFormatting.RED);
			}
			case OTHER_ALREADY_OWN_PLOT:
			{
				message.appendText("This plot is already owned by ");
				ChatComponentText townName = new ChatComponentText(info[0]);
				townName.getChatStyle().setItalic(true);
				message.appendSibling(townName);
				message.appendText("!");
				style.setColor(EnumChatFormatting.RED);
			}
			case YOU_ALREADY_OWN_PLOT:
			{
				message.appendText("You already own this plot!");
				style.setColor(EnumChatFormatting.RED);
			}
			case PLOT_ACQUIRED:
			{
				message.appendText("You already own this plot!");
				style.setColor(EnumChatFormatting.GREEN);
			}
		}
		
		player.addChatMessage(message);
	}
}
