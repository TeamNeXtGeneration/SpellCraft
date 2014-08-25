package de.castelbuilder123.spellcraft.utils.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Jona on 25.08.14.
 */
public class TinyButton {
    IIcon inactive;
    IIcon active;
    IIcon icon;
    int x,y,width, height;
    public TinyButton(int x, int y, int width, int height, IIcon icon)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon = icon;
        Minecraft mc = Minecraft.getMinecraft();
        inactive = mc.getResourceManager().getResource(new ResourceLocation(""))
    }

    public void update(GuiScreen screen)
    {

    }

    public void render(GuiScreen screen)
    {
        screen.drawTexturedModelRectFromIcon(); // x,y, icon, width, height
    }
}
