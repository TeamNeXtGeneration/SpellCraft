package de.castelbuilder123.spellcraft.registers;

import cpw.mods.fml.common.registry.VillagerRegistry;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.entity.EntityBoss;
import de.castelbuilder123.spellcraft.entity.EntityHellDog;
import de.castelbuilder123.spellcraft.entity.VillageTradeHandler;
import net.minecraft.util.ResourceLocation;

public class EntityRegistery
{
	private static boolean registered = false;
	public static void Register()
	{
		if (!registered)
		{
			registered = true;
            VillagerRegistry.instance().registerVillagerId(1119); // King
            if (!SpellCraftMod.proxy.runsOnServer())
                VillagerRegistry.instance().registerVillagerSkin(1119, new ResourceLocation(String.format("%s:textures/mobs/villagers/King.png", SpellCraftMod.MODID)));
            VillagerRegistry.instance().registerVillageTradeHandler(1119, new VillageTradeHandler());
			cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoss.class, "Devil", 0, SpellCraftMod.instance, 25, 1, true); // We need that, because after 1.7 registerGlobalEntityID will go away
            cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityHellDog.class, "HellDog", 1, SpellCraftMod.instance, 25, 1, true);
			//TODO: Add registration Stuff and Things.
		}
	}
	public static boolean getRegistered()
	{
		return registered;
	}
}