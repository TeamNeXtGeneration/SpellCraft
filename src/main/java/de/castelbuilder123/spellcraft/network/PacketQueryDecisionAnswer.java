package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by Jona on 22.08.14.
 */
public class PacketQueryDecisionAnswer implements IMessage {
    public int d;
    public String u;

    @Override
    public void fromBytes(ByteBuf buf) {
        u=ByteBufUtils.readUTF8String(buf);
        d=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, u);
        buf.writeInt(d);
    }
}
