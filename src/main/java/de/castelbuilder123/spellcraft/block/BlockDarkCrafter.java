package de.castelbuilder123.spellcraft.block;

import cpw.mods.fml.common.asm.transformers.SideTransformer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.block.tile.TileDarkCrafter;
import de.castelbuilder123.spellcraft.gui.DarkCrafter;
import de.castelbuilder123.spellcraft.utils.anticheat.Redecision;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Jona on 08.09.14.
 */
public class BlockDarkCrafter extends BlockContainer {
    IIcon top;
    IIcon bottom;
    public BlockDarkCrafter()
    {
        super(Material.wood);
        setCreativeTab(SpellCraftMod.tabSpellCraftDark);
        setHarvestLevel("axe", 0);
        setHardness(2.5f);
        GameRegistry.registerTileEntity(TileDarkCrafter.class, this.getUnlocalizedName());
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        dropItems(world, x, y, z);
    }

    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        dropItems(world,x,y,z);
    }


    @Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (side == 1) // Top
        {
            return top;
        }
        else if (side == 0) // Bottom
        {
            return bottom;
        }
        else
        {
            return blockIcon;
        }
    }

    private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world,
                        x + rx, y + ry, z + rz,
                        new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".blockDarkCrafter";
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        blockIcon = register.registerIcon(SpellCraftMod.MODID + ":IconBlockDarkCrafter_Side");
        top = register.registerIcon(SpellCraftMod.MODID + ":IconBlockDarkCrafter_Top");
        bottom = register.registerIcon(SpellCraftMod.MODID + ":IconBlockDarkCrafter_Bottom");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileDarkCrafter();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player == null)
            return false;

        Redecision.Sync(player.getDisplayName());
        if (Redecision.GetPlayerDecision(player.getDisplayName()) == 2)
        {
            player.openGui(SpellCraftMod.instance, DarkCrafter.GUI_ID, world, x, y, z);
            return true;
        }

        return false;
    }
}
