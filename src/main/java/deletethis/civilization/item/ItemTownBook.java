package deletethis.civilization.item;

import java.util.List;

import deletethis.civilization.Plot;
import deletethis.civilization.Town;
import deletethis.civilization.exception.PlotAlreadyHasOwnerException;
import deletethis.civilization.exception.PlotAlreadyRegisteredException;
import deletethis.civilization.exception.TownAlreadyExistsException;
import deletethis.civilization.util.UtilMessage;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemTownBook extends Item
{
	public ItemTownBook()
	{
		setHasSubtypes(true);
		setUnlocalizedName("town_book");
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return ((stack.getMetadata() == 0 ? "" : EnumChatFormatting.AQUA) + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + (stack.getMetadata() == 0 ? "" : "_created");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems) 
	{
		subItems.add(new ItemStack(item, 1, 0));
		subItems.add(new ItemStack(item, 1, 1));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(!stack.hasTagCompound())
			return stack;
		
		if(stack.getMetadata() == 0)
		{
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
				UtilMessage.sendCantCreateTownHereMessage(player, e.getTown().getName());
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
	
			stack.setItemDamage(1);
			stack.getTagCompound().setString("founder", player.getGameProfile().getName());
			
			return stack;
		}
		else if(stack.getMetadata() == 1)
		{
			Town town = new Town(stack.getTagCompound().getString("townname"));
			CivilizationWorldData data = CivilizationWorldData.get(world);
			for(Town townIterator : data.getTowns())
			{
				if(townIterator.equals(town))
					town = townIterator;
			}
	        int dimension = world.provider.getDimensionId();
	        int x = world.getChunkFromBlockCoords(player.getPosition()).xPosition;
	        int z = world.getChunkFromBlockCoords(player.getPosition()).zPosition;
	        Plot plot = new Plot(dimension, x, z);
	        
			try {town.addPlot(plot, data.getTowns());}
			catch (PlotAlreadyRegisteredException e) 
			{
				UtilMessage.sendYouAlreadyOwnThisPlotMessage(player);
				return stack;
			}
			catch (PlotAlreadyHasOwnerException e)
			{
				UtilMessage.sendCantAddThisPlotMessage(player, e.getTown().getName());
				return stack;
			}
			
			UtilMessage.sendPlotAcquiredMessage(player);
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
			 if(stack.getMetadata() == 1 && !(stack.getTagCompound().getString("founder").isEmpty()))
			 {
				 String founder = stack.getTagCompound().getString("founder");
				 tooltip.add("Founder: " + founder);
			 }
		 }
	}
}
