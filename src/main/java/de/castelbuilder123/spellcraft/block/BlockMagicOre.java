package de.castelbuilder123.spellcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.utils.anticheat.Redecision;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.util.Random;

public class BlockMagicOre extends Block {
    IIcon blockIcon;
    IIcon blockIconGood;
    IIcon blockIconBad;
    public BlockMagicOre() {
        super(Material.rock);
        setCreativeTab(SpellCraftMod.tabSpellCraft);
        setHardness(3.0F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 2);
        this.setBlockTextureName(SpellCraftMod.MODID + ":IconBlockMagicOreStone");

    }

    @Override
    public Item getItemDropped(int p1, Random p2, int p3)
    {
        return null;
    }

    @Override
    public String getUnlocalizedName()
    {
        return SpellCraftMod.MODID + ".blockMagicOre";
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        blockIcon = register.registerIcon(SpellCraftMod.MODID + ":IconBlockMagicOreStone");
        blockIconGood = register.registerIcon(SpellCraftMod.MODID + ":IconBlockMagicOreBright");
        blockIconBad = register.registerIcon(SpellCraftMod.MODID + ":IconBlockMagicOreDark");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        int decision = Redecision.GetPlayerDecision(Minecraft.getMinecraft().thePlayer.getDisplayName());
        if (decision == 1)
            return blockIconGood;
        else if (decision == 2)
            return blockIconBad;
        else
            return blockIcon;

    }
}
