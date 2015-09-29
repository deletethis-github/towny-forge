package deletethis.civilization.item;

import java.util.List;

import deletethis.civilization.Town;
import deletethis.civilization.exception.PlotAlreadyHasOwnerException;
import deletethis.civilization.exception.TownAlreadyExistsException;
import deletethis.civilization.util.UtilMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ItemFoundingBook extends Item
{
	public ItemFoundingBook()
	{
		setUnlocalizedName("founding_book");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(!stack.hasTagCompound())
			return stack;
		
		String townname = stack.getTagCompound().getString("townname");
		
		try
		{
			Town.create(townname, world, player);
		}
		catch (TownAlreadyExistsException e)
		{
			UtilMessage.sendTownAlreadyExistsMessage(player, townname);
			return stack;
		}
		catch (PlotAlreadyHasOwnerException e)
		{
			UtilMessage.sendPlotAlreadyHasOwnerMessage(player, e.getTown().getName());
			return stack;
		}
        
		if(!world.isRemote)
		{
			@SuppressWarnings({ "unchecked" })
			List<EntityPlayerMP> list = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
			for (EntityPlayerMP iteratorPlayer : list)
			{
				UtilMessage.sendTownCreatedMessage(iteratorPlayer, townname);
			}
		}

		return stack;
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
