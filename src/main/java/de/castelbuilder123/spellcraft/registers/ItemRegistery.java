package de.castelbuilder123.spellcraft.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import de.castelbuilder123.spellcraft.item.*;
import net.minecraft.item.Item;

public class ItemRegistery
{
	private static boolean registered = false;

    public static Item TestItem;
    public static Item KeyItem;
    public static Item DevilSummoningStaffItem;
    public static Item DevilTridentItem;
    public static Item SourceOfLightItem;
    public static Item PowerOfTheDarknessItem;
    public static Item PowerOfTheBrightItem;
    public static Item BookOfMagicItem;
    public static Item BookOfDarkMagicItem;

	public static void Register()
	{
		if (!registered)
		{
			registered = true;
            TestItem = new ItemTest();
            GameRegistry.registerItem(TestItem, TestItem.getUnlocalizedName());
            KeyItem = new ItemKey();
            GameRegistry.registerItem(KeyItem, KeyItem.getUnlocalizedName());
			DevilSummoningStaffItem = new ItemDevilSummoningStaff();
            GameRegistry.registerItem(DevilSummoningStaffItem, DevilSummoningStaffItem.getUnlocalizedName());
            DevilTridentItem = new ItemDevilTrident();
            GameRegistry.registerItem(DevilTridentItem, DevilTridentItem.getUnlocalizedName());
            SourceOfLightItem = new ItemSourceOfLight();
            GameRegistry.registerItem(SourceOfLightItem, SourceOfLightItem.getUnlocalizedName());
            PowerOfTheDarknessItem = new ItemPowerOfTheDarkness();
            GameRegistry.registerItem(PowerOfTheDarknessItem, PowerOfTheDarknessItem.getUnlocalizedName());
            PowerOfTheBrightItem = new ItemPowerOfTheBright();
            GameRegistry.registerItem(PowerOfTheBrightItem, PowerOfTheBrightItem.getUnlocalizedName());
            BookOfMagicItem = new ItemBookOfMagic();
            GameRegistry.registerItem(BookOfMagicItem, BookOfMagicItem.getUnlocalizedName());
            BookOfDarkMagicItem = new ItemBookOfDarkMagic();
            GameRegistry.registerItem(BookOfDarkMagicItem, BookOfDarkMagicItem.getUnlocalizedName());
		}
	}
	public static boolean getRegistered()
	{
		return registered;
	}
}