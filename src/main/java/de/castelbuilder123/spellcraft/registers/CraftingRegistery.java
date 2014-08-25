package de.castelbuilder123.spellcraft.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingRegistery
{
	private static boolean registered = false;
	
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
	
	public static boolean getRegistered()
	{
		return registered;
	}
}