package de.castelbuilder123.spellcraft.data;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

/**
 * Created by Jona on 12.08.14.
 */
public class MapData extends WorldSavedData {
    public static final String IDENTIFIER = SpellCraftMod.MODID;

    private NBTTagCompound data = new NBTTagCompound();

    public MapData() {
        super(IDENTIFIER);
    }

    public MapData(String identifier) {
        super(identifier);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        data = nbt.getCompoundTag(IDENTIFIER);
        SpellCraftMod.log.info("Loading data");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setTag(IDENTIFIER, data);
        SpellCraftMod.log.info("Saving Data");
    }

    public NBTTagCompound getData()
    {
        return data;
    }
}