package de.castelbuilder123.spellcraft.entity;

import cpw.mods.fml.common.registry.VillagerRegistry;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.registers.BlockRegistery;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

/**
 * Created by Jona on 04.08.14.
 */
public class VillageTradeHandler implements VillagerRegistry.IVillageTradeHandler {
    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        SpellCraftMod.log.info("VillagerProfession: " + villager.getProfession());
        int villagerID = villager.getProfession();
        if (villagerID == 1119) // King
        {
            villager.setCustomNameTag("The King of this Village");
            recipeList.add(new MerchantRecipe(new ItemStack(Items.diamond_sword), new ItemStack(Items.emerald, 10), new ItemStack(BlockRegistery.BossPortal)));
        }
    }
}
