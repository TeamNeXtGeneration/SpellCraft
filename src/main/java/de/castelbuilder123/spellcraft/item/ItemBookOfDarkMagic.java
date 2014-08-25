package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.gui.BookDark;
import de.castelbuilder123.spellcraft.utils.anticheat.Redecision;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBookOfDarkMagic extends Item {
    public ItemBookOfDarkMagic() {
        super();
        setCreativeTab(SpellCraftMod.tabSpellCraftDark);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName() {
        return SpellCraftMod.MODID + ".itemBookOfDarkMagic";
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return SpellCraftMod.MODID + ".itemBookOfDarkMagic";
    }

    @Override
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemBookOfDarkMagic");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        if (Redecision.GetPlayerDecision(player.getDisplayName()) == 2)
        {
            player.openGui(SpellCraftMod.instance, BookDark.GUI_ID, world, (int)player.posX,  (int)player.posY,  (int)player.posZ);
        }
        return item;
    }
}
