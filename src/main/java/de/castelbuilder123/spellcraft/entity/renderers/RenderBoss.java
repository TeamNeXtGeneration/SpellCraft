package de.castelbuilder123.spellcraft.entity.renderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.entity.models.ModelBoss;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
@SideOnly(Side.CLIENT)
public class RenderBoss extends RenderLiving {

    private ModelBoss bossModel;

    private static final ResourceLocation bossTexture = new ResourceLocation(SpellCraftMod.MODID+":textures/mobs/boss/Devil.png");

    public RenderBoss(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
        if (par1ModelBase instanceof ModelBoss)
        {
            bossModel = (ModelBoss)par1ModelBase;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return bossTexture;
    }
}
