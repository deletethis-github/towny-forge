package deletethis.civilization.item;

import java.util.List;

import deletethis.civilization.Town;
import deletethis.civilization.exception.TownAlreadyExistsException;
import deletethis.civilization.util.UtilMessage;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class ItemFoundingBook extends Item
{
	public ItemFoundingBook()
	{
		setUnlocalizedName("founding_book");
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(!stack.hasTagCompound())
			return false;
		
        CivilizationWorldData data = CivilizationWorldData.get(world);
        
        String townname = stack.getTagCompound().getString("townname");
        Town town = new Town(townname);
        
        try
		{
			data.addTown(town);
		}
		catch (TownAlreadyExistsException e)
		{
			UtilMessage.send(player, "A town with the name of " + townname + " already exists!", EnumChatFormatting.RED);
			return false;
		}
        
        UtilMessage.send(player, "You have created the town of " + townname + "!", EnumChatFormatting.AQUA);
        
		return true;
    }
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player	)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		stack.setTagCompound(nbt);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) 
	{
		 if(stack.hasTagCompound())
		 {
			 String townname = stack.getTagCompound().getString("townname");
			 tooltip.add("Town: " + townname);
		 }
	}
}
