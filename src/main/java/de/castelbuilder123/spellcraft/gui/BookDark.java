package de.castelbuilder123.spellcraft.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.data.persistend.client.BookData;
import de.castelbuilder123.spellcraft.utils.gui.Grid;
import de.castelbuilder123.spellcraft.utils.gui.TinyButton;
import de.castelbuilder123.spellcraft.utils.gui.TinyButtonInfo;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class BookDark extends GuiScreen {
    int oldMouseX = 0, oldMouseY = 0;

    public static int GUI_ID = 3;

    Grid grid;

    public BookDark() {


    }

    @Override
    public void keyTyped(char Char, int keyCode)
    {
        if (keyCode == Keyboard.KEY_BACK)
        {
            BookData.renderID = -1;
        }
        super.keyTyped(Char, keyCode);
    }

    @Override
    public void handleMouseInput()
    {
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        super.handleMouseInput();
        oldMouseX = x;
        oldMouseY = y;
    }

    public void initGui() {
        this.buttonList.clear();
        grid = new Grid((int)(width*.25), (int)(height*.25), (int)(width*.5), (int)(height*.5), new ResourceLocation(SpellCraftMod.MODID+":textures/gui/DarkBG.png"));
        // GUIButton Constructor: ButtonID, x,y, width, height, text
        grid.buttonList.add(new TinyButtonInfo(-8,-8, new TinyButton(0,0,16,16, SpellCraftMod.MODID+":textures/gui/dark/GetStarted16.png")));
    }

    public void updateScreen() {
        if (BookData.renderID == -1)
        {
            grid.x = (int)(width*.25);
            grid.y = (int)(height*.25);
            grid.width = (int)(width*.5);
            grid.height = (int)(height*.5);
            grid.update(this);
        }
        super.updateScreen();
    }


    protected void actionPerformed(GuiButton b) {

    }

    public void drawScreen(int p1, int p2, float p3) {
        this.drawWorldBackground(0x000000);
        if (BookData.renderID == -1)
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
        if (BookData.renderID == -1)
        {
            //SpellCraftMod.log.info("[GUI] [BookDark] MouseClick: " + x + ", " + y + ": " + button);
            if (button == 1) // Rightclick = 1; Leftclick = 0; Mousewheel: 2
            {
                int buttonIDpressed = grid.clicked(x, y, this);
                if (buttonIDpressed != -1)
                {
                    BookData.renderID = buttonIDpressed;
                }
            }
        }
        super.mouseClicked(x,y,button);
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long timeSinceMouseButtonPress) {
        if (BookData.renderID == -1)
        {
            //SpellCraftMod.log.info("Mouse: " +x + " " + y + " " + button + " " + timeSinceMouseButtonPress);
            if (button == 0)
            {
                int changeX = oldMouseX - x;
                int changeY = oldMouseY - y;
                grid.centerX -= changeX;
                grid.centerY -= changeY;
            }
        }
    }
}
