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
		}
	}
	public static boolean getRegistered()
	{
		return registered;
	}
}