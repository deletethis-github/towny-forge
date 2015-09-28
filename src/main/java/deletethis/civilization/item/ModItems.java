package deletethis.civilization.item;

import deletethis.civilization.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static ItemFoundingBook itemFoundingBook;
	
	public static void registerItems()
	{
		itemFoundingBook = (ItemFoundingBook)new ItemFoundingBook();
		GameRegistry.registerItem(itemFoundingBook, itemFoundingBook.getUnlocalizedName().substring(5));
	}
	
	public static void registerItemModels()
	{
		ItemModelMesher m = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		m.register(ModItems.itemFoundingBook, 0, new ModelResourceLocation(ModInfo.ID + ":" + itemFoundingBook.getUnlocalizedName().substring(5), "inventory"));
	}
}
