package de.castelbuilder123.spellcraft.proxies;

import cpw.mods.fml.common.network.IGuiHandler;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.gui.DecisionScreen;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import de.castelbuilder123.spellcraft.network.PacketOpenGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ServerProxy extends Proxy implements IGuiHandler
{
	public ServerProxy()
	{
		Proxy.setInstance((Proxy)this);
	}
	
	public World getWorld()
	{
		World world = null;
		try
		{
			world = MinecraftServer.getServer().worldServers[0];
			SpellCraftMod.log.info("Searching for server side world reference: Success!");
		}
		catch (Exception ex)
		{
			SpellCraftMod.log.error("Error while getting server side world reference");
		}
		return world;
	}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == DecisionScreen.GUI_ID)
        {
            PacketOpenGUI toSend = new PacketOpenGUI();
            toSend.GUIID = ID;
            PacketHandler.INSTANCE.sendTo(toSend, (EntityPlayerMP) player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == DecisionScreen.GUI_ID)
        {
            return new DecisionScreen();
        }
        return null;
    }
}