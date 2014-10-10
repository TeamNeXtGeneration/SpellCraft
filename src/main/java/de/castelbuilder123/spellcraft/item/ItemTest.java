package de.castelbuilder123.spellcraft.item;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.block.tile.TileDarkCrafter;
import de.castelbuilder123.spellcraft.registers.BlockRegistery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by Jona on 04.08.14.
 */
public class ItemTest extends Item {
    public ItemTest()
    {
        super();
        setCreativeTab(CreativeTabs.tabAllSearch);
        setMaxStackSize(1);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".itemTest";
    }

    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        return SpellCraftMod.MODID + ".itemTest";
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int meta, float clickX, float clickY, float clickZ)
    {
        if (world.getBlock(x,y,z) == BlockRegistery.DarkCrafter)
        {
            player.addChatMessage(new ChatComponentText("DarkCrafter Power:" + ((TileDarkCrafter)world.getTileEntity(x,y,z)).Power));
        }
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        if (!SpellCraftMod.proxy.runsOnServer())
        {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Client:"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Entity 1: " + p_77644_2_.getClass().toString() + " (Health: "+p_77644_2_.getHealth()+")"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Entity 2: " + p_77644_3_.getClass().toString() + " (Health: "+p_77644_3_.getHealth()+")"));
        }
        else
        {
            if (p_77644_3_ instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP)p_77644_3_;
                player.addChatMessage(new ChatComponentText("Server:"));
                player.addChatMessage(new ChatComponentText("Entity 1: " + p_77644_2_.getClass().toString() + " (Health: "+p_77644_2_.getHealth()+")"));
                player.addChatMessage(new ChatComponentText("Entity 2: " + p_77644_3_.getClass().toString() + " (Health: "+p_77644_3_.getHealth()+")"));
            }
        }
        return false;
    }

    @Override
    public boolean isDamageable()
    {
        return false;
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(SpellCraftMod.MODID + ":IconItemTest");
    }
}
