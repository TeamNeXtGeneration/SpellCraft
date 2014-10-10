package de.castelbuilder123.spellcraft.utils.gui;

import net.minecraft.client.gui.GuiScreen;

public interface WikiPage {
    public void init(GuiScreen screen);
    public void update(GuiScreen screen);
    public void render(GuiScreen screen);
    public void onClick(int x, int y, int button);
    public void onClose();
    public void onKeyPress(char key, int keyCode);
}
