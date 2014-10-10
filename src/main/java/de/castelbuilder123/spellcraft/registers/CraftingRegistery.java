package de.castelbuilder123.spellcraft.registers;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingRegistery
{
	private static boolean registered = false;
	
	public static List<CustomCraft> craftingReceipes = new ArrayList<CustomCraft>();

	public static void Register()
	{
		if (!registered)
		{
            GameRegistry.addRecipe(new ItemStack(ItemRegistery.DevilSummoningStaffItem),
                    "ddd",
                    "dnd",
                    "ddd",
                    'd', new ItemStack(Items.diamond), 'n', new ItemStack(Items.nether_star));
            GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistery.BookOfMagicItem), new ItemStack(ItemRegistery.PowerOfTheBrightItem), new ItemStack(Items.book));
            GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistery.BookOfDarkMagicItem), new ItemStack(ItemRegistery.PowerOfTheDarknessItem), new ItemStack(Items.book));
			registered = true;
		}
	}
	
	public static void RegisterCustomCraftingRecipe(boolean dark, int energyReq, ItemStack[] items, ItemStack fusion1, ItemStack fusion2, ItemStack out)
	{
		craftingReceipes.add(new CustomCraft(dark, energyReq, items, fusion1, fusion2, out));
	}
	
	public static boolean isValidCraft(boolean dark, ItemStack[] items, ItemStack fusion1, ItemStack fusion2)
	{
		for (CustomCraft craft : craftingReceipes)
		{
			boolean isValid = false;
			List<Item> itemsReq = new ArrayList<Item>();
			for (ItemStack itemstack : craft.items)
			{
				itemsReq.add(itemstack.getItem());
			}
			boolean fusion1match = false, fusion2match = false;
			if (fusion1.getItem().equals(craft.fusion1.getItem()))
			{
				fusion1match = true;
			}
			if (fusion2.getItem().equals(craft.fusion2.getItem()))
			{
				fusion2match = true;
			}
			if (fusion1match&&fusion2match && craft.dark == dark)
			{
				boolean itemsValid = false;
				int id = 0;
				for (ItemStack item: items)
				{
					if (item != null)
					{
						if (item.getItem()==itemsReq.toArray()[id])
						{
							itemsValid = true;
						}
						else
						{
							itemsValid = false;
						}
						id++;
					}
					else if (itemsReq.toArray()[id] == null)
					{
						itemsValid = true;
					}
				}
				isValid = itemsValid;
			}
			else
				isValid = false;
			if (isValid) 
			{
				return isValid;
			}
		}
		return false;
	}
	
	public static boolean getRegistered()
	{
		return registered;
	}
}