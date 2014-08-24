package de.castelbuilder123.spellcraft.entity.renderers;

/**
 * Created by Jona on 11.08.14.
 */

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
@SideOnly(Side.CLIENT)
public class RenderHellDog extends RenderLiving {

    private ModelWolf dogModel;

    private static final ResourceLocation dogTexture = new ResourceLocation(SpellCraftMod.MODID+":textures/mobs/HellDog.png");

    public RenderHellDog(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
        if (par1ModelBase instanceof ModelWolf)
        {
            dogModel = (ModelWolf)par1ModelBase;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return dogTexture;
    }
}
