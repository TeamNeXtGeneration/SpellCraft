package de.castelbuilder123.spellcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.block.tile.TileKeyReceptical;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Jona on 08.08.14.
 */
public class BlockKeyStone extends Block {
    public IIcon blockIcon;
    public BlockKeyStone()
    {
        super(Material.rock);
        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(SpellCraftMod.tabSpellCraft);
        this.setHarvestLevel("pickaxe", 0);
        this.setBlockTextureName(SpellCraftMod.MODID + ":IconBlockPortalBoss");
        this.setBlockUnbreakable();
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".blockKeyStone";
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        blockIcon = register.registerIcon(SpellCraftMod.MODID + ":IconBlockKeyStone");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return blockIcon;
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        int keyCounter = 0;
        int keyHoleCount = 0;
        // Get block above
        if (world.getBlock(x,y+1,z) instanceof BlockKeyReceptical)
        {
            keyHoleCount++;
            TileEntity tile = world.getTileEntity(x, y + 1, z);
            if (tile != null)
            {
                if (tile instanceof TileKeyReceptical)
                {
                    TileKeyReceptical tileKeyReceptical = (TileKeyReceptical)tile;
                    if (tileKeyReceptical.hasKey)
                    {
                        keyCounter++;
                    }
                }
            }
        }
        // Get block below
        if (world.getBlock(x,y-1,z) instanceof BlockKeyReceptical)
        {
            keyHoleCount++;
            TileEntity tile = world.getTileEntity(x, y - 1, z);
            if (tile != null)
            {
                if (tile instanceof TileKeyReceptical)
                {
                    TileKeyReceptical tileKeyReceptical = (TileKeyReceptical)tile;
                    if (tileKeyReceptical.hasKey)
                    {
                        keyCounter++;
                    }
                }
            }
        }
        // Get block left
        if (world.getBlock(x-1,y,z) instanceof BlockKeyReceptical)
        {
            keyHoleCount++;
            TileEntity tile = world.getTileEntity(x-1, y, z);
            if (tile != null)
            {
                if (tile instanceof TileKeyReceptical)
                {
                    TileKeyReceptical tileKeyReceptical = (TileKeyReceptical)tile;
                    if (tileKeyReceptical.hasKey)
                    {
                        keyCounter++;
                    }
                }
            }
        }
        // Get block right
        if (world.getBlock(x+1,y,z) instanceof BlockKeyReceptical)
        {
            keyHoleCount++;
            TileEntity tile = world.getTileEntity(x+1, y, z);
            if (tile != null)
            {
                if (tile instanceof TileKeyReceptical)
                {
                    TileKeyReceptical tileKeyReceptical = (TileKeyReceptical)tile;
                    if (tileKeyReceptical.hasKey)
                    {
                        keyCounter++;
                    }
                }
            }
        }
        // Get block behind
        if (world.getBlock(x,y,z+1) instanceof BlockKeyReceptical)
        {
            keyHoleCount++;
            TileEntity tile = world.getTileEntity(x, y, z+1);
            if (tile != null)
            {
                if (tile instanceof TileKeyReceptical)
                {
                    TileKeyReceptical tileKeyReceptical = (TileKeyReceptical)tile;
                    if (tileKeyReceptical.hasKey)
                    {
                        keyCounter++;
                    }
                }
            }
        }
        // Get block in front
        if (world.getBlock(x,y,z-1) instanceof BlockKeyReceptical)
        {
            keyHoleCount++;
            TileEntity tile = world.getTileEntity(x, y, z-1);
            if (tile != null)
            {
                if (tile instanceof TileKeyReceptical)
                {
                    TileKeyReceptical tileKeyReceptical = (TileKeyReceptical)tile;
                    if (tileKeyReceptical.hasKey)
                    {
                        keyCounter++;
                    }
                }
            }
        }

        if (keyCounter == keyHoleCount)
        {
            Block block1 = world.getBlock(x-1,y,z);
            Block block2 = world.getBlock(x+1,y,z);
            Block block3 = world.getBlock(x,y-1,z);
            Block block4 = world.getBlock(x,y+1,z);
            Block block5 = world.getBlock(x,y,z-1);
            Block block6 = world.getBlock(x,y,z+1);
            if (block1 instanceof BlockKeyReceptical)
            {
                world.func_147480_a(x-1, y, z, false); // Not deobfuscated! world.destroyBlock(x,y,z,drops?);
            }
            if (block2 instanceof BlockKeyReceptical)
            {
                world.func_147480_a(x+1, y, z, false); // Not deobfuscated! world.destroyBlock(x,y,z,drops?);
            }
            if (block3 instanceof BlockKeyReceptical)
            {
                world.func_147480_a(x, y-1, z, false); // Not deobfuscated! world.destroyBlock(x,y,z,drops?);
            }
            if (block4 instanceof BlockKeyReceptical)
            {
                world.func_147480_a(x, y+1, z, false); // Not deobfuscated! world.destroyBlock(x,y,z,drops?);
            }
            if (block5 instanceof BlockKeyReceptical)
            {
                world.func_147480_a(x, y, z-1, false); // Not deobfuscated! world.destroyBlock(x,y,z,drops?);
            }
            if (block6 instanceof BlockKeyReceptical)
            {
                world.func_147480_a(x, y, z+1, false); // Not deobfuscated! world.destroyBlock(x,y,z,drops?);
            }
            world.func_147480_a(x,y,z, false); // Destroy me!
        }
        return true; // Suppress normal action
    }

}