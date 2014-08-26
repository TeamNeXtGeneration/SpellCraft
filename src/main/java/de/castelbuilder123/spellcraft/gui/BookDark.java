package de.castelbuilder123.spellcraft.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.utils.gui.Grid;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class BookDark extends GuiScreen {
    int oldMouseX = 0, oldMouseY = 0;

    public static int GUI_ID = 3;

    Grid grid;

    public BookDark() {


    }

    public void initGui() {
        this.buttonList.clear();
        grid = new Grid((int)(width*.25), (int)(height*.25), (int)(width*.5), (int)(height*.5), new ResourceLocation(SpellCraftMod.MODID+":textures/gui/DarkBG.png"));
        // GUIButton Constructor: ButtonID, x,y, width, height, text
    }

    public void updateScreen() {
        grid.x = (int)(width*.25);
        grid.y = (int)(height*.25);
        grid.width = (int)(width*.5);
        grid.height = (int)(height*.5);
        grid.update(this);
        super.updateScreen();
    }


    protected void actionPerformed(GuiButton b) {

    }

    public void drawScreen(int p1, int p2, float p3) {
        this.drawWorldBackground(0x000000);
        grid.render(this);
        super.drawScreen(p1, p2, p3);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void mouseClicked(int x, int y, int button)
    {
        if (button == 0)
        {
            int buttonIDpressed = grid.clicked(x, y, this);
            if (buttonIDpressed != -1)
            {

            }
        }
        super.mouseClicked(x,y,button);
    }

    @Override
    public void mouseMovedOrUp(int x, int y, int button)
    {
        super.mouseMovedOrUp(x,y,button);
    }
    @Override
    protected void mouseClickMove(int x, int y, int button, long timeSinceMouseButtonPress) {
        //SpellCraftMod.log.info("Mouse: " +x + " " + y + " " + button + " " + timeSinceMouseButtonPress);
        if (button == 0)
        {
            int changeX = oldMouseX - x;
            int changeY = oldMouseY - y;
            grid.centerX -= changeX;
            grid.centerY -= changeY;
        }
        oldMouseX = x;
        oldMouseY = y;
    }
}
