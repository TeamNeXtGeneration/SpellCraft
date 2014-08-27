package de.castelbuilder123.spellcraft.utils.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
        setButtonPos();
        for (TinyButtonInfo b: buttonList)
            b.button.update(screen);
    }

    private void setButtonPos() {
        int screenCenterX = this.x+this.width/2;
        int screenCenterY = this.y+this.height/2;
        for (TinyButtonInfo buttonInfo: buttonList)
        {
            buttonInfo.button.x = screenCenterX + centerX + buttonInfo.x;
            buttonInfo.button.y = screenCenterY + centerY + buttonInfo.y;
        }
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
                if (x == this.x || y == this.y || x >= this.x+width-16 || y >= this.y+height-16)
                {
                    screen.drawTexturedModalRect(x,y,0,0,16,16);
                }
            }
        }
    }

    private void renderBG(GuiScreen screen) {
        screen.mc.renderEngine.bindTexture(bgImage);
        //GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        //GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
        //GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        //GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        screen.drawTexturedModalRect(x+16,y+16, 0,0, width-16, height-16);
    }

    public int clicked(int x, int y, GuiScreen screen)
    {
        int id;
        for (id = 0; id < buttonList.size(); id++)
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
        //return true;
        return (x < b.x && y < b.y && x+width > b.x+b.width && y+height > b.y+b.height);
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
