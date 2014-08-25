package de.castelbuilder123.spellcraft.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.registers.BlockRegistery;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class SpellCraftOreGenerator implements IWorldGenerator {
    public static final int Rareness = 64;
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0)
        {
            for (int i = 0; i < Rareness; i++)
            {
                int x = (random.nextInt(16)+1)+chunkX*16;
                int y = (random.nextInt(64)+1);
                int z = (random.nextInt(16)+1)+chunkZ*16;
                if (world.getBlock(x,y,z) instanceof BlockStone)
                {
                    int minVeinSize = 4;
                    int maxVeinSize = 8;
                    new WorldGenMinable(BlockRegistery.MagicOre, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), Blocks.stone).generate(world, random, x, y, z);
                }
            }
        }
    }
}