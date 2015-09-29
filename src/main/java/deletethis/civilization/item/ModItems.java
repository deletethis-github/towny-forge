package deletethis.civilization.item;

import deletethis.civilization.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static ItemTownBook itemTownBook;
	
	public static void registerItems()
	{
		itemTownBook = (ItemTownBook)new ItemTownBook();
		GameRegistry.registerItem(itemTownBook, itemTownBook.getUnlocalizedName().substring(5));
	}
	
	public static void addVariantNames()
	{
		ModelBakery.addVariantName(itemTownBook, 
			ModInfo.ID + ":" + itemTownBook.getUnlocalizedName(new ItemStack(itemTownBook, 1, 0)).substring(5), 
			ModInfo.ID + ":" + itemTownBook.getUnlocalizedName(new ItemStack(itemTownBook, 1, 1)).substring(5));
	}
	
	public static void registerItemModels()
	{
		ItemModelMesher m = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		m.register(ModItems.itemTownBook, 0, new ModelResourceLocation(ModInfo.ID + ":" + itemTownBook.getUnlocalizedName(new ItemStack(itemTownBook, 1, 0)).substring(5), "inventory"));
		m.register(ModItems.itemTownBook, 1, new ModelResourceLocation(ModInfo.ID + ":" + itemTownBook.getUnlocalizedName(new ItemStack(itemTownBook, 1, 1)).substring(5), "inventory"));
	}
}
