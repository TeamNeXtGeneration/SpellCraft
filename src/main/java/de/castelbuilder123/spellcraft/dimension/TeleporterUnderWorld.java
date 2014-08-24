package de.castelbuilder123.spellcraft.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/**
 * Created by Jona on 05.08.14.
 */
public class TeleporterUnderWorld extends Teleporter {
    private final WorldServer worldServer;

    public TeleporterUnderWorld(WorldServer worldServer) {
        super(worldServer);
        this.worldServer = worldServer;
    }

    @Override
    public boolean makePortal(Entity entity)
    {
        //SpellCraftMod.log.info("Making portal in dimension: " + worldServer.provider.dimensionId);
        //if (worldServer.provider.dimensionId == -2)
        //{
        //    SpellCraftMod.log.info("Creating portal in dimension: " + worldServer.provider.dimensionId);
        //    worldServer.setBlock(0,128,0, BlockRegistery.BossPortal);
        //}
        return true;
    }

    @Override
    public boolean placeInExistingPortal(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_)
    {
        /*
        SpellCraftMod.log.info("Place in existing portal in dimension: " + worldServer.provider.dimensionId);
        if (worldServer.getBlock(0,128,0).getMaterial() == Material.air)
        {
            makePortal(p_77184_1_);
            placeInPortal(p_77184_1_, p_77184_2_, p_77184_4_, p_77184_6_, p_77184_8_);
        } */
        placeInPortal(p_77184_1_, p_77184_2_, p_77184_4_, p_77184_6_, p_77184_8_);
        return true;
    }

    @Override
    public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_)
    {
        //SpellCraftMod.log.info("Place in portal in dimension: " + worldServer.provider.dimensionId);
        if (p_77185_1_.dimension == -2)
            p_77185_1_.setPosition(0.5D, 131D, 0.5D);
        else
        {
            if (p_77185_1_ instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP)p_77185_1_;
                final ChunkCoordinates bedLocation = player.getBedLocation(0);
                if (bedLocation != null)
                    p_77185_1_.setPosition(bedLocation.posX, bedLocation.posY, bedLocation.posZ);
                else
                {
                    final ChunkCoordinates spawnPoint = p_77185_1_.worldObj.getSpawnPoint();
                    p_77185_1_.setPosition(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ);
                }
            }
            else {
                final ChunkCoordinates spawnPoint = p_77185_1_.worldObj.getSpawnPoint();
                p_77185_1_.setPosition(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ);
            }
        }
    }
}
