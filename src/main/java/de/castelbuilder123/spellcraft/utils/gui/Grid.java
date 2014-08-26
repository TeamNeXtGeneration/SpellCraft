package de.castelbuilder123.spellcraft.utils.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class Grid{
    public int x;
    public int y;
    public int width;
    public int height;
    private final ResourceLocation bgImage;
    public int centerX=0, centerY=0;
    public List<TinyButtonInfo> buttonList = new ArrayList<TinyButtonInfo>();
    public Grid(int x, int y, int width, int height, ResourceLocation bgImage)
    {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bgImage = bgImage;
    }

    public void update(GuiScreen screen)
    {
        //setButtonPos();
        for (TinyButtonInfo b: buttonList)
            b.button.update(screen);
    }

    public void render(GuiScreen screen)
    {
        renderBG(screen);
        for (TinyButtonInfo b: buttonList)
        {
            if (canBeRendered(b.button))
            {
                b.button.render(screen);
            }
        }
        renderWalls(screen);
    }

    private void renderWalls(GuiScreen screen) {
        screen.mc.renderEngine.bindTexture(new ResourceLocation(SpellCraftMod.MODID + ":textures/gui/Edge.png"));
        for (int x = this.x; x < this.x+width; x+=16)
        {
            for (int y = this.y; y < this.y+height; y+=16)
            {
                if (x+16 < this.x+width || y+16 < this.y+height)
                {
                    screen.drawTexturedModalRect(x,y,0,0,16,16);
                }
                else
                {
                    screen.drawTexturedModalRect(x,y,0,0,(x-(this.x+width)),(y-(this.y+height)));
                }
            }
        }
    }

    private void renderBG(GuiScreen screen) {
        screen.mc.renderEngine.bindTexture(bgImage);
        screen.drawTexturedModalRect(x+16,y+16, 0,0, width-16, height-16);
    }

    public int clicked(int x, int y, GuiScreen screen)
    {
        int id;
        for (id = 0; id < buttonList.size()-1; id++)
        {
            if (buttonList.get(id).button.isPressed(getRelMouseX(x, screen.width), getRelMouseY(y, screen.height)))
            {
                return id;
            }
        }
        return -1;
    }

    private boolean canBeRendered(TinyButton b)
    {
        return (x > b.x && y > b.y && x+width-16 > b.x+b.width && y+height-16 > b.y+b.height);
    }

    private int getRelMouseX(int x, int width)
    {
        return x;
    }

    private int getRelMouseY(int y, int height)
    {
        return y;
    }
}
