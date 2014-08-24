package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.entity.EntityBoss;
import de.castelbuilder123.spellcraft.registers.ItemRegistery;
import de.castelbuilder123.spellcraft.utils.anticheat.Redecision;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDevilSummoningStaff extends Item {
    public ItemDevilSummoningStaff()
    {
        super();
        setCreativeTab(SpellCraftMod.tabSpellCraft);
        setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack tool,
                             EntityPlayer player, World world, int x, int y,
                             int z, int par7, float xFloat, float yFloat, float zFloat)
    {
        if (Redecision.hasDecided(player.getDisplayName()))
            return false;
        if (world.provider.dimensionId == -2)
        {
            if (canBeUsed(x,y,z))
            {
                if (!world.isRemote)
                {
                    Entity ent = new EntityBoss(world);
                    ent.setPosition(x+.5D,y+2D,z+.5D);
                    world.spawnEntityInWorld(ent);
                }
                if (!player.capabilities.isCreativeMode)
                    player.inventory.consumeInventoryItem(ItemRegistery.DevilSummoningStaffItem);
                return true;
            }
        }
        return false;
    }

    public boolean canBeUsed(int x, int y, int z)
    {
        return x >= 63 && x <= 67 && y == 127 && z >= -4 && z <= 4;
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".itemDevilSummoningStaff";
    }

    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        return SpellCraftMod.MODID + ".itemDevilSummoningStaff";
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemDevilSummoningStaff");
    }
}
