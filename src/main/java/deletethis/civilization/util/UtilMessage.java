package deletethis.civilization.util;

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
	
	public static void sendTownCreatedMessage(EntityPlayer player, String town)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText message = new ChatComponentText("The town of ");
			ChatComponentText one = new ChatComponentText(town);
			one.getChatStyle().setItalic(true);
			ChatComponentText two = new ChatComponentText(" has been created!");
			message.appendSibling(one);
			message.appendSibling(two);
			message.getChatStyle().setColor(EnumChatFormatting.AQUA);
			player.addChatMessage(message);
		}
	}
	
	public static void sendTownAlreadyExistsMessage(EntityPlayer player, String town)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText message = new ChatComponentText("A town with the name of ");
			ChatComponentText one = new ChatComponentText(town);
			one.getChatStyle().setItalic(true);
			ChatComponentText two = new ChatComponentText(" already exists!");
			message.appendSibling(one);
			message.appendSibling(two);
			message.getChatStyle().setColor(EnumChatFormatting.RED);
			player.addChatMessage(message);
		}
	}
	
	public static void sendCantCreateTownHereMessage(EntityPlayer player, String town)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText message = new ChatComponentText("This plot is already owned by ");
			ChatComponentText one = new ChatComponentText(town);
			one.getChatStyle().setItalic(true);
			ChatComponentText two = new ChatComponentText("! You can't create a town here.");
			message.appendSibling(one);
			message.appendSibling(two);
			message.getChatStyle().setColor(EnumChatFormatting.RED);
			player.addChatMessage(message);
		}
	}
	
	public static void sendCantAddThisPlotMessage(EntityPlayer player, String town)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText message = new ChatComponentText("This plot is already owned by ");
			ChatComponentText one = new ChatComponentText(town);
			one.getChatStyle().setItalic(true);
			ChatComponentText two = new ChatComponentText("! You can't add this plot to your town.");
			message.appendSibling(one);
			message.appendSibling(two);
			message.getChatStyle().setColor(EnumChatFormatting.RED);
			player.addChatMessage(message);
		}
	}
	
	public static void sendYouAlreadyOwnThisPlotMessage(EntityPlayer player)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText message = new ChatComponentText("You already own this plot!");
			message.getChatStyle().setColor(EnumChatFormatting.RED);
			player.addChatMessage(message);
		}
	}
	
	public static void sendPlotAcquiredMessage(EntityPlayer player)
	{
		World world = player.worldObj;
		if(!world.isRemote)
		{
			ChatComponentText message = new ChatComponentText("Plot acquired!");
			message.getChatStyle().setColor(EnumChatFormatting.GREEN);
			player.addChatMessage(message);
		}
	}
}
