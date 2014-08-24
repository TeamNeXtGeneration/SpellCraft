package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import de.castelbuilder123.spellcraft.SpellCraftMod;

/**
 * Created by Jona on 22.08.14.
 */
public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SpellCraftMod.MODID);

    public static void init() {
        //Register Packets Here
        PacketHandler.INSTANCE.registerMessage(MessageHandlerSendDecision.class, PacketDecision.class, 1, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage(MessageHandlerSendQueryDecision.class, PacketQueryDecision.class, 2, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage(MessageHandlerRecvDecisionAnswer.class, PacketQueryDecisionAnswer.class, 3, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(MessageHandlerRecvOpenGUI.class, PacketOpenGUI.class, 4, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(MessageHandlerRecvKillEntity.class, PacketKillEntity.class, 5, Side.CLIENT);
    }
}
