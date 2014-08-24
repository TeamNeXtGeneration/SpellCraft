package de.castelbuilder123.spellcraft.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Created by Jona on 04.08.14.
 */
public class WorldProviderUnderWorld extends WorldProvider {
    int DimID = -2;

    public String getDimensionName()
    {
        return "Underworld";
    }
    public boolean canRespawnHere()
    {
        return false; // Back to Overworld
    }
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.8F);
        this.dimensionId = DimID;
        this.hasNoSky = true;
    }
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderUnderWorld(worldObj, worldObj.getSeed(), true);
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2)
    {
        return false;
    }
    public boolean renderStars()
    {
        return false;
    }
    public boolean renderClouds()
    {
        return false;
    }
    public boolean renderVoidFog()
    {
        return true;
    }
    public boolean renderEndSky()
    {
        return true;
    }
    public float setSunSize()
    {
        return 0F;
    }
    public float setMoonSize()
    {
        return 0F;
    }
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return -30000.0F; // Cant see them
    }
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return new ChunkCoordinates(0, 8, 0);
    }

    @SideOnly(Side.CLIENT)
    public String getWelcomeMessage()
    {
        if ((this instanceof WorldProviderUnderWorld))
        {
            return EnumChatFormatting.DARK_PURPLE+"Muhahahahaha";
        }
        return null;
    }
}
