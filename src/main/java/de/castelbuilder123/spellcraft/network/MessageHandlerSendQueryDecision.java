package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.data.PlayerData;
import de.castelbuilder123.spellcraft.data.PlayerNBTData;

/**
 * Created by Jona on 23.08.14.
 */
public class MessageHandlerSendQueryDecision implements IMessageHandler<PacketQueryDecision, IMessage> {
    @Override
    public IMessage onMessage(PacketQueryDecision message, MessageContext ctx) {
        for (PlayerNBTData data : PlayerData.playerNBTDatas)
            if (data.username.equals(message.u)) {
                PacketQueryDecisionAnswer toSend = new PacketQueryDecisionAnswer();
                toSend.u = message.u;
                toSend.d = data.decision;
                PacketHandler.INSTANCE.sendToAll(toSend);
                break;
            }
        return null;
    }
}
