package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPowerOfTheDarkness extends Item {
    public ItemPowerOfTheDarkness(){

        super();
        setCreativeTab(SpellCraftMod.tabSpellCraft);
        setMaxStackSize(64);
    }


    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".itemPowerOfTheDarkness";
    }

    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        return SpellCraftMod.MODID + ".itemPowerOfTheDarkness";
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemPowerOfTheDarkness");
    }
}
