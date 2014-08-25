package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.utils.anticheat.Redecision;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBookOfMagic extends Item {
    public ItemBookOfMagic() {
        super();
        setCreativeTab(SpellCraftMod.tabSpellCraftBright);
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

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        if (Redecision.GetPlayerDecision(player.getDisplayName()) != 1)
        {
            player.setFire(5); // The light set you in flame
            player.addPotionEffect(new PotionEffect(15, 200, 10, false)); //Blindness, because too much light
            player.addPotionEffect(new PotionEffect(9, 200, 10, false)); //Nausea
        }
        else
        {

        }
        return item;
    }
}
