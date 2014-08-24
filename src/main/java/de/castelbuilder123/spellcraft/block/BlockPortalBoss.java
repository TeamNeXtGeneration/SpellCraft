package de.castelbuilder123.spellcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.dimension.TeleporterUnderWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Jona on 04.08.14.
 */
public class BlockPortalBoss extends Block {
    public IIcon blockIcon;
    public BlockPortalBoss()
    {
        super(Material.rock);
        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(SpellCraftMod.tabSpellCraft);
        this.setHarvestLevel("pickaxe", 0);
        this.setBlockTextureName(SpellCraftMod.MODID + ":IconBlockPortalBoss");

    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".blockPortalBoss";
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        blockIcon = register.registerIcon(SpellCraftMod.MODID + ":IconBlockPortalBoss");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return blockIcon;
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if(player.ridingEntity == null && player.riddenByEntity == null && player instanceof EntityPlayerMP)
        {
            for (int slot = 0; slot < 35; slot++) {
                final ItemStack inSlot = player.inventory.getStackInSlot(slot);
                if (inSlot != null && inSlot.getItem() == new ItemStack(this).getItem())
                    player.inventory.setInventorySlotContents(slot, null);
            }
            MinecraftServer server = MinecraftServer.getServer();
            player.timeUntilPortal = 10;
            if (player.dimension != -2)
                server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)player, -2, new TeleporterUnderWorld(server.worldServerForDimension(-2))); // GoTo The Underworld
            else
                server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)player, 0,  new TeleporterUnderWorld(server.worldServerForDimension(0))); // GoTo The Overworld
        }
        return true; // Suppress normal action
    }

}
