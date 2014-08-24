package de.castelbuilder123.spellcraft.dimension;

import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderFlat;

public class ChunkProviderUnderWorld extends ChunkProviderFlat
{
    private World world;
    private long seed;
    private boolean whatever;

    public ChunkProviderUnderWorld(World world, long seed, boolean b)
    {
        super(world, seed, b, "2;7,125x10");
        this.world = world;
        this.seed = seed;
        this.whatever = b;
    }
}
