package de.castelbuilder123.spellcraft.registers;

import de.castelbuilder123.spellcraft.dimension.WorldProviderUnderWorld;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by Jona on 04.08.14.
 */
public class DimensionRegistery {
    private static boolean registered = false;

    public static void Register()
    {
        if (!registered)
        {
            registered = true;
            DimensionManager.registerProviderType(-2, WorldProviderUnderWorld.class, false);
            DimensionManager.registerDimension(-2, -2);
        }
    }

    public static boolean getRegistered()
    {
        return registered;
    }
}
