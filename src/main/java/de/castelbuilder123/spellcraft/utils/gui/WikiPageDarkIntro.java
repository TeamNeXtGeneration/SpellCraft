package de.castelbuilder123.spellcraft.utils.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class WikiPageDarkIntro implements WikiPage {

    int oldWidth;
    String[] lines = new String[0];

    @Override
    public void init(GuiScreen screen) {

    }

    @Override
    public void update(GuiScreen screen) {
        if (oldWidth != (int)(screen.height*.5))
        {
            lines = WikiPageUtils.StringToMultiline(screen, "Hello and welcome to SpellCraft, this book functions as wiki. To start get a crafting bench and shift right click with your trident", (int)(screen.height*.5)-10);
            oldWidth = (int)(screen.height*.5);
        }
    }

    @Override
    public void render(GuiScreen screen) {
        WikiPageUtils.RenderBG(screen, (int)(screen.width*.5-(screen.height*.5/2)), (int)(screen.height*.1), (int)(screen.height*.5), (int)(screen.height*.8));
        screen.drawCenteredString(screen.mc.fontRenderer, "The Beginning", screen.width/2, (int) (screen.height*.1+5), 0xFF0000);
        int height = (int)(screen.height*.1+25);
        for (String draw: lines)
        {
            screen.drawString(screen.mc.fontRenderer, draw, (int)(screen.width*.5-(screen.height*.5/2))+5, height, 0xFFFFFF);
            height += 10;
        }
    }

    @Override
    public void onClick(int x, int y, int button) {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void onKeyPress(char key, int keyCode) {

    }
}
