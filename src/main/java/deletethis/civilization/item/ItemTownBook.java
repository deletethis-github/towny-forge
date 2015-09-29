package deletethis.civilization.item;

import java.util.List;

import deletethis.civilization.CivilizationObjectFactory;
import deletethis.civilization.Plot;
import deletethis.civilization.Town;
import deletethis.civilization.util.CivilizationMessageSender;
import deletethis.civilization.util.EnumMessage;
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
	public static enum EnumVariant
	{
		CREATABLE(0, "town_book_creatable"),
		CREATED(1, "town_book_created");
		
		private int metadata;
		private String unlocalizedName;
		
		public static final EnumVariant[] index = new EnumVariant[values().length];
		
		public int getMetaData()
		{
			return metadata;
		}
		
		public String getUnlocalizedName()
		{
			return unlocalizedName;
		}
		
		private EnumVariant(int metadata, String unlocalizedName)
		{
			this.metadata = metadata;
			this.unlocalizedName = unlocalizedName;
		}
	}
	
	
	public static EnumVariant getVariantFromMetadata(int metadata)
	{
		return EnumVariant.index[metadata];
	}
	
	public ItemTownBook()
	{
		setUnlocalizedName("town_book");
		setMaxDamage(0);
		setHasSubtypes(true);
		this.setMaxStackSize(1);
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack stack)
    {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append((stack.getMetadata() == EnumVariant.CREATED.getMetaData() ? EnumChatFormatting.AQUA : ""));
		stringBuilder.append(StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim());
        return stringBuilder.toString();
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		EnumVariant variant = getVariantFromMetadata(stack.getMetadata());
		return variant.getUnlocalizedName();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems) 
	{
		for(EnumVariant variant : EnumVariant.index)
		{
			subItems.add(new ItemStack(item, 1, variant.getMetaData()));
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(stack.getMetadata() == ItemTownBook.EnumVariant.CREATABLE.getMetaData())
		{
			CivilizationWorldData data = CivilizationWorldData.get(world);
			Plot plot = CivilizationObjectFactory.createPlot(world, player.playerLocation);
			NBTTagCompound nbt = stack.getTagCompound();
			String townName = nbt.getString("townname");
			
			if(data.townExists(townName))
			{
				CivilizationMessageSender.send(player, EnumMessage.TOWN_ALREADY_EXISTS, townName);
				return stack;
			}

			if(plot.isOwned())
			{
				CivilizationMessageSender.send(player, EnumMessage.OTHER_ALREADY_OWN_PLOT, townName);
				return stack;
			}
			
			Town town = CivilizationObjectFactory.createTown(townName, player);
			data.addTown(town);
			
			stack.setItemDamage(EnumVariant.CREATED.getMetaData());
			nbt.setString("founder", player.getGameProfile().getName());

			if(!world.isRemote)
			{
				@SuppressWarnings("unchecked")
				List<EntityPlayerMP> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
				for(EntityPlayerMP playerIteration : players)
				{
					CivilizationMessageSender.send(playerIteration, EnumMessage.TOWN_CREATED, townName);
				}
			}
			
			return stack;
		}
		
		if(stack.getMetadata() == ItemTownBook.EnumVariant.CREATED.getMetaData())
		{
			CivilizationWorldData data = CivilizationWorldData.get(world);
			Plot plot = CivilizationObjectFactory.createPlot(world, player.playerLocation);
			NBTTagCompound nbt = stack.getTagCompound();
			String townName = nbt.getString("townname");
			
			Town town = data.getTown(townName);
			
			if(town == null) return stack;
			
			if(town.hasPlot(plot))
			{
				CivilizationMessageSender.send(player, EnumMessage.YOU_ALREADY_OWN_PLOT, townName);
				return stack;
			}
			
			if(plot.isOwned())
			{
				CivilizationMessageSender.send(player, EnumMessage.OTHER_ALREADY_OWN_PLOT, townName);
				return stack;
			}
			
			town.addPlot(plot);
			CivilizationMessageSender.send(player, EnumMessage.PLOT_ACQUIRED, townName);
			
			return stack;
		}

		return stack;
    }
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		stack.setTagCompound(new NBTTagCompound());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) 
	{
		NBTTagCompound nbt = stack.getTagCompound();
		String townName = nbt.getString("townname");
		if(!townName.isEmpty())
		{
			tooltip.add("Town: " + townName);
		}
		String founder = nbt.getString("founder");
		if(!founder.isEmpty())
		{
			tooltip.add("Founder: " + founder);
		}
	}
}
