package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.data.PlayerData;
import de.castelbuilder123.spellcraft.data.PlayerNBTData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Jona on 22.08.14.
 */
public class PacketDecision implements IMessage {
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
