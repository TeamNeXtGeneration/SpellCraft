package de.castelbuilder123.spellcraft.data;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Jona on 24.08.14.
 */
public class ExtendedPlayer implements IExtendedEntityProperties {
    public final static String EXT_PROP_NAME = "ExtendedPlayer";

    @Override
    public void saveNBTData(NBTTagCompound compound) {

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

    }

    @Override
    public void init(Entity entity, World world) {

    }
}
