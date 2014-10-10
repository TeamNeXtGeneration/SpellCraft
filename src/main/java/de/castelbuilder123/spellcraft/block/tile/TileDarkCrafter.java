package de.castelbuilder123.spellcraft.block.tile;

import com.sun.corba.se.impl.interceptors.SlotTableStack;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.registers.ItemRegistery;
import de.castelbuilder123.spellcraft.utils.anticheat.Redecision;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

public class TileDarkCrafter extends TileEntity implements IInventory{

    public ItemStack[] slots = new ItemStack[13];
    public int Power = 0;


    public TileDarkCrafter()
    {
        super();
    }

    @Override
    public void updateEntity()
    {
        if (slots[0] == null)
        {
            super.updateEntity();
            return;
        }
        if (slots[0].stackSize > 0 && slots[0].getItem() == ItemRegistery.PowerOfTheDarknessItem)
        {
            if (Power <= 900)
            {
                slots[0].stackSize--;
                Power += 100;
            }
        }
        super.updateEntity();
    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList tagList = compound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < slots.length) {
                slots[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }

        Power = compound.getInteger("Power");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < slots.length; i++) {
            ItemStack stack = slots[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        compound.setTag("Inventory", itemList);
        compound.setInteger("Power", Power);
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
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slots[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        slots[slot] = itemStack;
    }

    @Override
    public String getInventoryName() {
        return SpellCraftMod.MODID+".darkCrafter";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        Redecision.Sync(player.getDisplayName());
        return Redecision.GetPlayerDecision(player.getDisplayName()) == 2;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        switch (slot)
        {
            case 1: // Energy Slot
                return itemStack.getItem() == ItemRegistery.PowerOfTheDarknessItem;
            case 13:
                return false;
            default:
                return true;
        }
    }
}
