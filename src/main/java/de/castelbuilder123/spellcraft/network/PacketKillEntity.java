package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by Jona on 22.08.14.
 */
public class PacketKillEntity implements IMessage {
    public int EntityID;

    @Override
    public void fromBytes(ByteBuf buf) {
        EntityID=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(EntityID);
    }
}
