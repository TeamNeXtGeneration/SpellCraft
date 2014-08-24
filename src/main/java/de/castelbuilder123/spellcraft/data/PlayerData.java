package de.castelbuilder123.spellcraft.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jona on 05.08.14.
 */
public class PlayerData {
    public static List<PlayerNBTData> playerNBTDatas = new ArrayList<PlayerNBTData>();

    public static void onJoin(EntityPlayer player)
    {
        NBTTagCompound dataReader = player.getEntityData();
        NBTBase decision = dataReader.getTag("SpellCraftDecision");
        if (decision == null)
            playerNBTDatas.add(new PlayerNBTData(player.getDisplayName(), 0));
        else
            playerNBTDatas.add(new PlayerNBTData(player.getDisplayName(), ((NBTTagInt)decision).func_150287_d()));
        //SpellCraftMod.log.info(dataReader.getInteger("decision"));
    }

    public static void onLogout(EntityPlayer player)
    {
        //SpellCraftMod.log.info("saving!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // NBT TAG NAME.StartsWith("SpellCraft") because of compatibility
        List<PlayerNBTData> toremove = new ArrayList<PlayerNBTData>();
        for (PlayerNBTData data: playerNBTDatas)
        {
            if (data.username == player.getDisplayName())
            {
                toremove.add(data);
                //SpellCraftMod.log.info("saved...");
                /*
                NBTTagCompound dataWriter = player.getEntityData();
                if (dataWriter.hasKey("SpellCraftDecision"))
                    dataWriter.setInteger("SpellCraftDecision", data.decision);
                else
                {
                    dataWriter.setTag("SpellCraftDecision", new NBTTagInt(data.decision));
                }
                playerNBTDatas.remove(data); // Throws ConcurrentModificationException
                */
            }
        }
        for (PlayerNBTData data: toremove) // Workaround for ConcurrentModificationException
        {
            NBTTagCompound dataWriter = player.getEntityData();
            if (dataWriter.hasKey("SpellCraftDecision"))
                dataWriter.setInteger("SpellCraftDecision", data.decision);
            else
            {
                dataWriter.setTag("SpellCraftDecision", new NBTTagInt(data.decision));
            }
            playerNBTDatas.remove(data);
        }
    }
}
