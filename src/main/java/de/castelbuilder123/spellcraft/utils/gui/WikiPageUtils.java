package de.castelbuilder123.spellcraft.utils.gui;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class WikiPageUtils {
    public static void RenderBG(GuiScreen screen, int x, int y, int width, int height)
    {
        screen.mc.renderEngine.bindTexture(new ResourceLocation(SpellCraftMod.MODID+":textures/gui/dark/WikiPageBG.png"));
        screen.drawTexturedModalRect(x,y,0,0,width,height);
    }

    public static String[] StringToMultiline(GuiScreen screen, String string, int width)
    {
        int lenght = 0;
        String[] returnment = new String[(int) Math.ceil(screen.mc.fontRenderer.getStringWidth(string)/width)+1];
        int stringIndex = 0;
        int stringArrayStart = 0;
        for (int i = 0; i < string.length(); i++)
        {
            int charlength = screen.mc.fontRenderer.getCharWidth(string.charAt(i));
            if (lenght + charlength > width)
            {
                returnment[stringIndex] = string.substring(stringArrayStart, i-1);
                stringIndex++;
                stringArrayStart = i-1;
                lenght = 0;
            }
            else
                lenght += charlength;
        }
        returnment[stringIndex] = string.substring(stringArrayStart, string.length());
        return returnment;
    }
}
