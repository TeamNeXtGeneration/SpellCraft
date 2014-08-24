package de.castelbuilder123.spellcraft.proxies;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.gui.DecisionScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Proxy implements IGuiHandler
{
	protected static Proxy proxyInstance = null;
	
	public static void setInstance(Proxy newProxy)
	{
		proxyInstance = newProxy;
	}
	
	public static Proxy getProxy()
	{
		if (proxyInstance == null)
		{
			proxyInstance = new Proxy();
		}
		return proxyInstance;
	}
	
	public void initSounds()    {}
	public void initRenderers() {}
	
	public World getWorld()
	{
		return null;
	}
	
	public static boolean runsOnServer()
	{
		boolean server = false;
		try
		{
			server = serverCheck();
		}
		catch (NoSuchMethodError e)
		{
			server = false;
		}
		return server;
	}
	
	@SideOnly(Side.SERVER)
	private static boolean serverCheck()
	{
		return true;
	}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

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