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
public class MessageHandlerSendDecision implements IMessageHandler<PacketDecision, IMessage> {
    @Override
    public IMessage onMessage(PacketDecision message, MessageContext ctx) {
            for (PlayerNBTData data : PlayerData.playerNBTDatas)
                if (data.username.equals(message.u)) {
                    data.decision = message.d;
                    break;
                }
        return null;
    }
}
