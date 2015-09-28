package deletethis.civilization.chat;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ChatComponentWarning extends ChatComponentText
{
	public ChatComponentWarning(String msg)
	{
		super(msg);
		ChatStyle style = this.getChatStyle();
		style.setColor(EnumChatFormatting.RED);
		style.setBold(true);
	}
}
