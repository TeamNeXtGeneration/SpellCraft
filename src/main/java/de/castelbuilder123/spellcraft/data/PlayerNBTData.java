package de.castelbuilder123.spellcraft.data;

public class PlayerNBTData {
    public String username = "Player";
    public int decision = 0; // 0 = not yet; 1 = Light Side; 2 = Dark Side

    public PlayerNBTData(String username, int decision)
    {
        this.username = username;
        this.decision = decision;
    }
}
