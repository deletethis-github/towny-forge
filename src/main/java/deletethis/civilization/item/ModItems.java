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
		String[] itemTownBookVariantUnlocalizedNames = new String[ItemTownBook.EnumVariant.index.length];
		for(int i = 0; i < ItemTownBook.EnumVariant.index.length; i++)
		{
			ItemTownBook.EnumVariant variant = ItemTownBook.EnumVariant.index[i];
			ItemStack stack = new ItemStack(itemTownBook, 1, variant.getMetaData());
			itemTownBookVariantUnlocalizedNames[i] = ModInfo.ID + ":" +  itemTownBook.getUnlocalizedName(stack).substring(5);
		}
		ModelBakery.addVariantName(itemTownBook, itemTownBookVariantUnlocalizedNames);
	}
	
	public static void registerItemModels()
	{
		ItemModelMesher m = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		
		for(ItemTownBook.EnumVariant variant : ItemTownBook.EnumVariant.index)
		{
			ItemStack stack = new ItemStack(itemTownBook, 1, variant.getMetaData());
			ModelResourceLocation model = new ModelResourceLocation(ModInfo.ID + ":" + itemTownBook.getUnlocalizedName(stack).substring(5), "inventory");
			m.register(itemTownBook, variant.getMetaData(), model);
		}
	}
}
