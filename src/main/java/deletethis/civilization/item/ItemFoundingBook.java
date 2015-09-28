package deletethis.civilization.item;

import java.util.List;

import deletethis.civilization.Plot;
import deletethis.civilization.Resident;
import deletethis.civilization.Town;
import deletethis.civilization.exception.PlotAlreadyOwnedException;
import deletethis.civilization.exception.ResidentAlreadyInTownException;
import deletethis.civilization.exception.TownAlreadyExistsException;
import deletethis.civilization.util.UtilMessage;
import deletethis.civilization.world.CivilizationWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
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
		
        CivilizationWorldData data = CivilizationWorldData.get(world);
        
        String townname = stack.getTagCompound().getString("townname");
        Town town = new Town(townname);
        
        // ATTEMPT TO ADD THE PLAYER AS A RESIDENT TO THE TOWN
        String uuid = player.getGameProfile().getId().toString();
        Resident resident = new Resident(uuid);
        try
		{
			town.addResident(resident);
		}
		catch (ResidentAlreadyInTownException e)
		{
		}
        
        // ATTEMPT TO ADD THE PLOT TO THE TOWN
        int dimension = world.provider.getDimensionId();
        int x = world.getChunkFromBlockCoords(player.getPosition()).xPosition;
        int z = world.getChunkFromBlockCoords(player.getPosition()).zPosition;
        Plot plot = new Plot(dimension, x, z);
        try
		{
			town.addPlot(plot);
		}
		catch (PlotAlreadyOwnedException e1)
		{
		}
        
        // ATTEMPT TO ADD THE TOWN TO WORLD DATA
        try
		{
			data.addTown(town);
		}
		catch (TownAlreadyExistsException e)
		{
			UtilMessage.send(player, "A town with the name of " + townname + " already exists!", EnumChatFormatting.RED);
			return stack;
		}
        
        UtilMessage.send(player, "You have created the town of " + townname + "!", EnumChatFormatting.AQUA);
        
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
