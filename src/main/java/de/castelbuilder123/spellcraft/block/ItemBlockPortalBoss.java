package de.castelbuilder123.spellcraft.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Jona on 06.08.14.
 */
public class ItemBlockPortalBoss extends ItemBlock {
    public ItemBlockPortalBoss(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
    {
        list.add("Don't have this block in");
        list.add("your inventory when you");
        list.add("travel to the Underworld.");
        list.add("It will be destroyed.");
    }
}
