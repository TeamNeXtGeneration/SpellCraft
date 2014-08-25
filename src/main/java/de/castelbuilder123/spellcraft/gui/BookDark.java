package de.castelbuilder123.spellcraft.gui;

import de.castelbuilder123.spellcraft.network.PacketDecision;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class BookDark extends GuiScreen {


    public static int GUI_ID = 3;

    public BookDark() {

    }

    public void initGui() {
        this.buttonList.clear();
        // GUIButton Constructor: ButtonID, x,y, width, height, text
    }

    public void updateScreen() {
        super.updateScreen();
    }


    protected void actionPerformed(GuiButton b) {

    }

    public void drawScreen(int p1, int p2, float p3) {
        this.drawWorldBackground(0x000000);
        super.drawScreen(p1, p2, p3);
    }
}
