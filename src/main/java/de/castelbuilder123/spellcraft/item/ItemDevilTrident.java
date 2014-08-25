package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDevilTrident extends Item
{
    public ItemDevilTrident()
    {
        super();
        setCreativeTab(SpellCraftMod.tabSpellCraftDark);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".itemDevilTrident";
    }

    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        return SpellCraftMod.MODID + ".itemDevilTrident";
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemDevilTrident");
    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        //SpellCraftMod.log.info(p_77644_2_.getClass().toString()); // Mob
        //SpellCraftMod.log.info(p_77644_3_.getClass().toString()); // Player
        p_77644_2_.setFire(3); // Its in seconds ... first were there 60... for 3 sec...
        return false;
    }
}
