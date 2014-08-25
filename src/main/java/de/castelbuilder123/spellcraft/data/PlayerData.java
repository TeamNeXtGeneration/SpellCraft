package de.castelbuilder123.spellcraft.data;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    public static List<PlayerNBTData> playerNBTDatas = new ArrayList<PlayerNBTData>();

    public static void onJoin(EntityPlayer player)
    {
        NBTTagCompound dataReader = player.getEntityData();
        if (dataReader.hasKey("SpellCraft"))
        {
            NBTTagCompound saveData = dataReader.getCompoundTag("SpellCraft");
            if (saveData.hasKey("Decision"))
                playerNBTDatas.add(new PlayerNBTData(player.getDisplayName(), saveData.getInteger("Decision")));
            else
                playerNBTDatas.add(new PlayerNBTData(player.getDisplayName(), 0));
        }
        else
            playerNBTDatas.add(new PlayerNBTData(player.getDisplayName(), 0));
        //SpellCraftMod.log.info(dataReader.getInteger("decision"));
    }

    public static void onLogout(EntityPlayer player)
    {
        SpellCraftMod.log.info("Saving Player Data for " + player.getDisplayName());
        List<PlayerNBTData> toremove = new ArrayList<PlayerNBTData>();
        for (PlayerNBTData data: playerNBTDatas)
        {
            if (data.username.equals(player.getDisplayName()))
            {
                SpellCraftMod.log.info("Found Player Data for " + player.getDisplayName());
                toremove.add(data);
            }
        }
        for (PlayerNBTData data: toremove) // Workaround for ConcurrentModificationException
        {
            NBTTagCompound dataWriter = player.getEntityData();
            NBTTagCompound saveData = new NBTTagCompound();
            saveData.setInteger("Decision", data.decision);
            dataWriter.setTag("SpellCraft", saveData);
            SpellCraftMod.log.info("Saved found Data (" + data.decision + ") for " + data.username);
            MinecraftServer server = MinecraftServer.getServer();
            if (server != null)
            {
                if (server.getConfigurationManager() != null)
                {
                    server.getConfigurationManager().saveAllPlayerData();
                    SpellCraftMod.log.info("Saving all Player Data to savegame...");
                }
                try
                {
                    int i;
                    WorldServer worldserver;
                    boolean flag;

                    for (i = 0; i < server.worldServers.length; ++i)
                    {
                        if (server.worldServers[i] != null)
                        {
                            worldserver = server.worldServers[i];
                            flag = worldserver.levelSaving;
                            worldserver.levelSaving = false;
                            worldserver.saveAllChunks(true, null);
                            worldserver.levelSaving = flag;
                        }
                    }
                }
                catch (MinecraftException minecraftexception)
                {
                    SpellCraftMod.log.error("Caught MineCraftException");
                    minecraftexception.printStackTrace();
                }
            }
            playerNBTDatas.remove(data);
        }
    }
}
