package de.castelbuilder123.spellcraft.block.tile;

import de.castelbuilder123.spellcraft.registers.ItemRegistery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Jona on 07.08.14.
 */
public class TileKeyReceptical extends TileEntity implements ISidedInventory {
    public boolean hasKey = false;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        //SpellCraftMod.log.info("Reading from NBT");
        hasKey = compound.getBoolean("hasKey");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        //SpellCraftMod.log.info("Writing to NBT");
        compound.setBoolean("hasKey", hasKey);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[] {0, 1};
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        if (p_102007_2_.getItem() == ItemRegistery.KeyItem) // Only keys
            return true;
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 2; // Slot 0 (extract), 1 (insert)
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        try
        {
            if (slot == 1)
                return (ItemStack)hasItem(true);
        }
        catch(Exception ex) { } // Nothing because of return null
        return null;
    }

    private Object hasItem(boolean asItemStack) {
        if (asItemStack)
        {
            if (hasKey)
                return new ItemStack(ItemRegistery.KeyItem, 1); // 1 Key
        }
        else
            return hasKey;
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        // Pipe!!!
        if (slot == 0)
            if (item == null)
                hasKey = false;
        else if (slot == 1)
                if (item.getItem() == ItemRegistery.KeyItem)
                    hasKey = true;
    }

    @Override
    public String getInventoryName() {
        return null; // No GUI
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false; // No GUI
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false; // No GUI
    }

    @Override
    public void openInventory() { } // No GUI

    @Override
    public void closeInventory() { } // Still no GUI

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item) {
        if (slot == 1 && item.getItem() == ItemRegistery.KeyItem)
            return true;
        return false;
    }
}
