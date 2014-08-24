package de.castelbuilder123.spellcraft.registers;

import cpw.mods.fml.common.registry.GameRegistry;
import de.castelbuilder123.spellcraft.block.BlockKeyReceptical;
import de.castelbuilder123.spellcraft.block.BlockKeyStone;
import de.castelbuilder123.spellcraft.block.BlockPortalBoss;
import de.castelbuilder123.spellcraft.block.ItemBlockPortalBoss;

public class BlockRegistery
{
	private static boolean registered = false;

    public static BlockPortalBoss BossPortal;
    public static BlockKeyReceptical KeyReceptical;
    public static BlockKeyStone KeyStone;

	public static void Register()
	{
		if (!registered)
		{
			registered = true;
            BossPortal = new BlockPortalBoss();
            GameRegistry.registerBlock(BossPortal, ItemBlockPortalBoss.class, BossPortal.getUnlocalizedName());
            KeyReceptical = new BlockKeyReceptical();
            GameRegistry.registerBlock(KeyReceptical, KeyReceptical.getUnlocalizedName());
            KeyStone = new BlockKeyStone();
            GameRegistry.registerBlock(KeyStone, KeyStone.getUnlocalizedName());
		}
	}
	public static boolean getRegistered()
	{
		return registered;
	}
}