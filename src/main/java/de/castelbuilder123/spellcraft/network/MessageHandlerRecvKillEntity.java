package de.castelbuilder123.spellcraft.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Created by Jona on 23.08.14.
 */
public class MessageHandlerRecvKillEntity implements IMessageHandler<PacketKillEntity, IMessage> {
    @Override
    public IMessage onMessage(PacketKillEntity message, MessageContext ctx) {
        Entity ent = getEntityByID(Minecraft.getMinecraft().theWorld, message.EntityID);
        if (ent != null)
        {
            Minecraft.getMinecraft().theWorld.removeEntity(ent);
        }
        else
            SpellCraftMod.log.error("ERROR: Ent = null!");

        return null;
    }

    private Entity getEntityByID(World world, int ID)
    {
        for (Object ent: world.getLoadedEntityList())
        {
            if (ent instanceof Entity)
            {
                if (((Entity) ent).getEntityId() == ID)
                {
                    return (Entity) ent;
                }
            }
        }
        return null;
    }
}
