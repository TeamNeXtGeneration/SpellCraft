package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.data.PlayerData;
import de.castelbuilder123.spellcraft.data.PlayerNBTData;
import net.minecraft.client.Minecraft;

/**
 * Created by Jona on 23.08.14.
 */
public class MessageHandlerRecvOpenGUI implements IMessageHandler<PacketOpenGUI, IMessage> {
    @Override
    public IMessage onMessage(PacketOpenGUI message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.openGui(SpellCraftMod.instance, message.GUIID, mc.theWorld, (int)mc.thePlayer.posX, (int)mc.thePlayer.posY, (int)mc.thePlayer.posZ);
        return null;
    }
}
