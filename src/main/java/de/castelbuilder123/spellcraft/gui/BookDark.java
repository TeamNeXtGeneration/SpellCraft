package de.castelbuilder123.spellcraft.gui;

import de.castelbuilder123.spellcraft.network.PacketDecision;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import de.castelbuilder123.spellcraft.utils.gui.Grid;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class BookDark extends GuiScreen {


    public static int GUI_ID = 3;

    public int oldMouseX = this.width/2;
    public int oldMouseY = this.height/2;

    public Grid grid = new Grid();

    public BookDark() {
        grid.centerX=0;
        grid.centerY=0;
    }

    public void initGui() {
        this.buttonList.clear();
        // GUIButton Constructor: ButtonID, x,y, width, height, text
    }

    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {
        int changeX = oldMouseX - mouseX;
        int changeY = oldMouseY - mouseY;
        grid.centerX -= changeX;
        grid.centerY -= changeY;
        //At the end
        oldMouseX = mouseX;
        oldMouseY = mouseY;
    }


    protected void actionPerformed(GuiButton b) {

    }

    public void drawScreen(int p1, int p2, float p3) {
        this.drawWorldBackground(0x000000);
        super.drawScreen(p1, p2, p3);
    }
}
