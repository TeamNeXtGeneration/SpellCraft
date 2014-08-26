package de.castelbuilder123.spellcraft.utils.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class TinyButton {
    String icon;
    public int x,y,width, height;
    Minecraft mc;
    public TinyButton(int x, int y, int width, int height, String iconResLoc)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon = iconResLoc;
        mc = Minecraft.getMinecraft();
    }

    public void update(GuiScreen screen)
    {
    }

    public void render(GuiScreen screen)
    {
        screen.mc.renderEngine.bindTexture(new ResourceLocation(String.format("%s:textures/gui/TinyButtonInActive.png", SpellCraftMod.MODID)));
        screen.drawTexturedModalRect(this.x, this.y, 0,0, width,height);
        screen.mc.renderEngine.bindTexture(new ResourceLocation(icon));
        SpellCraftMod.log.info("Mouse Pos: " + x + " " + y);
        screen.drawTexturedModalRect(this.x, this.y, 0,0, width,height);
    }

    public boolean isPressed(int mX, int mY)
    {
        return (mX > x && mX < x+width && mY > y && mY < y+height);
    }
}
