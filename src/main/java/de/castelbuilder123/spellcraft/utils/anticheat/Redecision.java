package de.castelbuilder123.spellcraft.utils.anticheat;

import de.castelbuilder123.spellcraft.data.PlayerData;
import de.castelbuilder123.spellcraft.data.PlayerNBTData;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import de.castelbuilder123.spellcraft.network.PacketQueryDecision;
import de.castelbuilder123.spellcraft.proxies.Proxy;

/**
 * Created by Jona on 24.08.14.
 */
public class Redecision {
    public static int GetPlayerDecision(String username)
    {
        for (PlayerNBTData d: PlayerData.playerNBTDatas)
        {
            if (d.username.equals(username))
            {
                return d.decision;
            }
        }
        return 0; // Should not be happening
    }

    public static boolean hasDecided(String username)
    {
        if (!Proxy.runsOnServer())
        {
            PacketQueryDecision packet = new PacketQueryDecision();
            packet.u = username;
            PacketHandler.INSTANCE.sendToServer(packet);
        }
        int decision = GetPlayerDecision(username);
        return decision == 1 || decision == 2;
    }

    public static void Sync(String displayName) {
        if (!Proxy.runsOnServer())
        {
            PacketQueryDecision packet = new PacketQueryDecision();
            packet.u = displayName;
            PacketHandler.INSTANCE.sendToServer(packet);
        }
    }
}
