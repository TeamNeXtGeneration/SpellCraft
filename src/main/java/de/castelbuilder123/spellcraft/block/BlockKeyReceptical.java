package de.castelbuilder123.spellcraft.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.block.tile.TileKeyReceptical;
import de.castelbuilder123.spellcraft.registers.ItemRegistery;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Jona on 07.08.14.
 */
public class BlockKeyReceptical extends BlockContainer {
    public IIcon blockIcon;

    public BlockKeyReceptical()
    {
        super(Material.rock);
        setCreativeTab(SpellCraftMod.tabSpellCraft);
        setHardness(Float.MAX_VALUE);
        setBlockUnbreakable(); // No coming through
        GameRegistry.registerTileEntity(TileKeyReceptical.class, this.getUnlocalizedName());
    }

    public BlockKeyReceptical(Material material)
    {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileKeyReceptical();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player == null)
            return false;

        TileKeyReceptical keyReceptical = (TileKeyReceptical)world.getTileEntity(x, y, z);

        if (player.getCurrentEquippedItem() != null)
        {
            ItemStack item = player.getCurrentEquippedItem();
            if (item != null)
            {
                if (item.getItem() == ItemRegistery.KeyItem)
                {
                    if (keyReceptical.hasKey)
                    {
                        keyReceptical.hasKey = false;
                        player.inventory.addItemStackToInventory(new ItemStack(ItemRegistery.KeyItem, 1));
                    }
                    else
                    {
                        keyReceptical.hasKey = true; // Block gets Key
                        if (!player.capabilities.isCreativeMode)
                            player.inventory.consumeInventoryItem(ItemRegistery.KeyItem); // Take Key
                    }
                }
            }

        }
        else if (keyReceptical.hasKey)
        {
            keyReceptical.hasKey = false;
            player.inventory.addItemStackToInventory(new ItemStack(ItemRegistery.KeyItem, 1));
        }
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return blockIcon;
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".blockKeyReceptical";
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        blockIcon = register.registerIcon(SpellCraftMod.MODID + ":IconBlockKeyReceptical");
    }

    public void useItem(EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {

            ItemStack item = player.inventory.mainInventory[player.inventory.currentItem];
            //Special cases
            if (item.getItem() == Items.milk_bucket)
            {
                player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(Items.bucket, 1);
            }
            else if (item.getItem() == Items.mushroom_stew)
            {
                player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(Items.bowl, 1);
            }
            //Generic case
            else
            {
                item.stackSize -= 1;

                if (item.stackSize == 0)
                {
                    item = null;
                }
            }
        }
    }

}
