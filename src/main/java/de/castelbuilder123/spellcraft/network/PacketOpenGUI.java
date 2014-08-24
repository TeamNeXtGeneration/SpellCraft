package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by Jona on 22.08.14.
 */
public class PacketOpenGUI implements IMessage {
    public int GUIID;

    @Override
    public void fromBytes(ByteBuf buf) {
        GUIID=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(GUIID);
    }
}
