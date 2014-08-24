package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPowerOfTheBright extends Item{
    public ItemPowerOfTheBright(){
        super();
        setCreativeTab(SpellCraftMod.tabSpellCraft);
        setMaxStackSize(64);
    }


    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".itemPowerOfTheBright";
    }

    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        return SpellCraftMod.MODID + ".itemPowerOfTheBright";
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemPowerOfTheBright");
    }
}
