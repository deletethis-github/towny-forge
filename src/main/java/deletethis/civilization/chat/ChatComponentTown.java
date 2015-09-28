package deletethis.civilization.chat;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ChatComponentTown extends ChatComponentText
{
	public ChatComponentTown(String msg)
	{
		super(msg);
		ChatStyle style = this.getChatStyle();
		style.setColor(EnumChatFormatting.GRAY);
		style.setItalic(true);
	}
}
