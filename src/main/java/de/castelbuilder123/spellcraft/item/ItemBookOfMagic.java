package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Jona on 25.08.14.
 */
public class ItemBookOfMagic extends Item {
    public ItemBookOfMagic() {
        super();
        setCreativeTab(SpellCraftMod.tabSpellCraft);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName() {
        return SpellCraftMod.MODID + ".itemBookOfMagic";
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return SpellCraftMod.MODID + ".itemBookOfMagic";
    }

    @Override
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemBookOfMagic");
    }
}
