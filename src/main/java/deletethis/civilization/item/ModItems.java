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
		itemFoundingBook = (ItemFoundingBook)new ItemFoundingBook().setUnlocalizedName("founding_book");
		GameRegistry.registerItem(itemFoundingBook, "founding_book");
	}
	
	public static void registerItemModels()
	{
		ItemModelMesher m = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		m.register(ModItems.itemFoundingBook, 0, new ModelResourceLocation(ModInfo.ID + ":" + "founding_book", "inventory"));
	}
}
