package de.castelbuilder123.spellcraft.gui;

import de.castelbuilder123.spellcraft.network.PacketDecision;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by Jona on 21.08.14.
 */
public class DecisionScreen extends GuiScreen {


    public static int GUI_ID = 1;
    public DecisionScreen()
    {

    }

    public void initGui()
    {
        this.buttonList.clear();
        // GUIButton Constructor: ButtonID, x,y, width, height, text
        this.buttonList.add(new GuiButton(1, (int)(this.width * .1), (int)(this.height*.5 - 20), (int)(this.width * .4) - 5, 20, "Stay on the bright side: Kill the Devil"));
        this.buttonList.add(new GuiButton(2, (int)(this.width * .5+5), (int)(this.height*.5 - 20), (int)(this.width * .4), 20, "Commit to the dark side: Accept the Gift"));
    }

    public void updateScreen()
    {
        super.updateScreen();
    }

    int d = 3;

    protected void actionPerformed(GuiButton b)
    {
        d = b.id;
        PacketDecision packetDecision = new PacketDecision();
        packetDecision.d = d;
        packetDecision.u = this.mc.thePlayer.getDisplayName();
        PacketHandler.INSTANCE.sendToServer(packetDecision);
        mc.thePlayer.closeScreen();
    }

    public void drawScreen(int p1, int p2, float p3)
    {
        this.drawCenteredString(mc.fontRenderer, "Devil", this.width / 2, 20, 0xFF0000);
        super.drawScreen(p1, p2, p3);
    }

    public int getDecision()
    {
        return d;
    }
}
