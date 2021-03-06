package de.castelbuilder123.spellcraft.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.block.container.ContainerDarkCrafter;
import de.castelbuilder123.spellcraft.block.tile.TileDarkCrafter;
import de.castelbuilder123.spellcraft.entity.EntityBoss;
import de.castelbuilder123.spellcraft.entity.EntityHellDog;
import de.castelbuilder123.spellcraft.entity.models.ModelBoss;
import de.castelbuilder123.spellcraft.entity.models.ModelHellDog;
import de.castelbuilder123.spellcraft.entity.renderers.RenderBoss;
import de.castelbuilder123.spellcraft.entity.renderers.RenderHellDog;
import de.castelbuilder123.spellcraft.gui.BookBright;
import de.castelbuilder123.spellcraft.gui.BookDark;
import de.castelbuilder123.spellcraft.gui.DarkCrafter;
import de.castelbuilder123.spellcraft.gui.DecisionScreen;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import de.castelbuilder123.spellcraft.network.PacketOpenGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class ClientProxy extends Proxy implements IGuiHandler
{
	public ClientProxy()
	{
		Proxy.setInstance((Proxy)this);
	}
	
	public void initSounds()
	{

	}
	
	public void initRenderers()
	{
        RenderingRegistry.registerEntityRenderingHandler(EntityBoss.class, new RenderBoss(new ModelBoss(), .5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellDog.class, new RenderHellDog(new ModelHellDog(), .5f));
	}
	
	public World getWorld()
	{
		World world = null;
		try
		{
			world = Minecraft.getMinecraft().theWorld;
			SpellCraftMod.log.info("Getting client side world: Success!");
		}
		catch (Exception ex) 
		{
			SpellCraftMod.log.error("Error on getting world client side");
		}
		return world;
	}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == DecisionScreen.GUI_ID)
        {
            PacketOpenGUI packet = new PacketOpenGUI();
            packet.GUIID = ID;
            PacketHandler.INSTANCE.sendTo(packet, (EntityPlayerMP) player);
        }
        else if (ID == DarkCrafter.GUI_ID)
        {
            try
            {
                return new ContainerDarkCrafter(player.inventory, (TileDarkCrafter) world.getTileEntity(x,y,z));
            }
            catch (ClassCastException ex)
            {
                SpellCraftMod.log.error("TileEntity != TileDarkCrafter");
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == DecisionScreen.GUI_ID)
        {
            return new DecisionScreen();
        }
        else if (ID == BookBright.GUI_ID){
            return new BookBright();
        }
        else if(ID == BookDark.GUI_ID){
            return new BookDark();
        }
        else if (ID == DarkCrafter.GUI_ID)
        {
            try
            {
                return new DarkCrafter(new ContainerDarkCrafter(player.inventory, (TileDarkCrafter) world.getTileEntity(x,y,z)));
            }
            catch (ClassCastException ex)
            {
                SpellCraftMod.log.error("TileEntity != TileDarkCrafter");
            }
        }
        return null;
    }
}